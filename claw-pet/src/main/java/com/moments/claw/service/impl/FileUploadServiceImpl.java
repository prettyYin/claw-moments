package com.moments.claw.service.impl;

import com.google.gson.Gson;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.utils.PathUtils;
import com.moments.claw.service.FileUploadService;
import com.moments.claw.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Data
@Slf4j
@Service
@ConfigurationProperties(prefix = "oss.qiniuyun")
public class FileUploadServiceImpl implements FileUploadService {

	private String accessKey;

	private String secretKey;

	private String bucket;

	private String fileUrl;

	@Override
	@Transactional
	public R<?> uploadAvatar(MultipartFile img) {
		String originalFilename = img.getOriginalFilename();
		FileUtils.checkFileEndWith(originalFilename);
		String filePath = PathUtils.generateFilePath(originalFilename,"avatars");
		String url = uploadToOSS(img, filePath);
		return R.success(url);
	}

	@Override
	@Transactional
	public R<?> uploadImg(MultipartFile img) {
		String originalFilename = img.getOriginalFilename();
		FileUtils.checkFileEndWith(originalFilename);
		String filePath = PathUtils.generateFilePath(originalFilename,"images");
		String url = uploadToOSS(img, filePath);
		return R.success(url);
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
