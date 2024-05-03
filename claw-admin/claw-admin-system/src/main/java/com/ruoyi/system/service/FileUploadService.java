package com.ruoyi.system.service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

	/**
	 * 上传头像
	 */
	String uploadAvatar(MultipartFile img);

	/**
	 * 上传文件
	 */
	String uploadImg(MultipartFile img);

	/**
	 * 批量上传文件
	 */
	List<String> uploadImgBatch(MultipartFile[] imgs);
}
