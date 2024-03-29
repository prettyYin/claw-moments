package com.moments.claw.service.impl;

import cn.hutool.core.lang.id.NanoId;
import com.google.gson.Gson;
import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.utils.PathUtils;
import com.moments.claw.service.FileUploadService;
import com.moments.claw.service.FilesService;
import com.moments.claw.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oss.qiniuyun")
public class FileUploadServiceImpl implements FileUploadService {

	private String accessKey;

	private String secretKey;

	private String bucket;

	private String fileUrl;

	private final FilesService filesService;

	/**
	 * 上传头像
	 * return 文件id
	 */
	@Override
	@Transactional
	public String uploadAvatar(MultipartFile img) {
		String originalFilename = img.getOriginalFilename();
		FileUtils.checkFileEndWith(originalFilename);
		String filePath = PathUtils.generateFilePath(originalFilename,"avatars");
		String fileId = NanoId.randomNanoId(GlobalConstants.FILE_ID_MAX_LENGTH);
		String furl = uploadToOSS(img, filePath);
		filesService.save(
				Files
						.builder()
						.fileId(fileId)
						.fileUrl(fileUrl + furl)
						.fileType(originalFilename.substring(originalFilename.lastIndexOf(".")))
						.fileSize(img.getSize())
						.status(GlobalConstants.NORMAL_STATUS)
						.build()
		);
		return fileId;
	}



	/**
	 * 上传图片
	 * return 文件id
	 */
	@Override
	@Transactional
	public String uploadImg(MultipartFile img) {
		String originalFilename = img.getOriginalFilename();
		FileUtils.checkFileEndWith(originalFilename);
		String filePath = PathUtils.generateFilePath(originalFilename,"images");
		String fileId = NanoId.randomNanoId(GlobalConstants.FILE_ID_MAX_LENGTH);
		String furl = uploadToOSS(img, filePath);
		filesService.save(
				Files
						.builder()
						.fileId(fileId)
						.fileUrl(fileUrl + furl)
						.fileName(originalFilename)
						.fileType(originalFilename.substring(originalFilename.lastIndexOf(".")))
						.fileSize(img.getSize())
						.status(GlobalConstants.NORMAL_STATUS)
						.build()
		);
		return fileId;
	}

	/**
	 * 批量上传图片
	 * return List 文件id列表
	 */
	@Transactional
	@Override
	public List<String> uploadImgBatch(MultipartFile[] imgs) {
		List<String> fileIds = new ArrayList<>();
		for (MultipartFile img : imgs) {
			fileIds.add(uploadImg(img));
		}
		return fileIds;
	}

	private String uploadToOSS(MultipartFile imgFile,String filePath) {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.autoRegion());
		cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
		//...其他参数参考类注释

		UploadManager uploadManager = new UploadManager(cfg);

		//默认不指定key的情况下，以文件内容的hash值作为文件名
		try {
			InputStream fileInputStream = imgFile.getInputStream();
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);

			try {
				Response response = uploadManager.put(fileInputStream, filePath, upToken, null, null);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				log.info("key====:{}",putRet.key); // key即是文件名
				log.info("hash====:{}",putRet.hash);
				return fileUrl + filePath; // 返回图片的外链地址
			} catch (QiniuException ex) {
				Response r = ex.response;
				log.error("文件上传出错了 =====> {}",r.toString());
				try {
					log.error("文件上传错误,内容 =====> {}",r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (Exception ex) {
			//ignore
		}
		return "upload error";
	}
}
