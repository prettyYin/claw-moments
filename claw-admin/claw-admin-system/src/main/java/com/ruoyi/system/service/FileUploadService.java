package com.ruoyi.system.service;
import com.moments.claw.domain.base.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

	/**
	 * 上传头像
	 */
	String uploadFileToDirectory(MultipartFile img);

	/**
	 * 上传文件
	 */
	String uploadImg(MultipartFile img);

	/**
	 * 批量上传文件
	 */
	List<String> uploadImgBatch(MultipartFile[] imgs);

	/**
	 * 上传文件到指定目录
	 */
	Files uploadFileToDirectory(MultipartFile img, String directory);
}
