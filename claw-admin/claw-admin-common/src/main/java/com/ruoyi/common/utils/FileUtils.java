package com.ruoyi.common.utils;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.file.FileException;
import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	private static String[] legalPattern = {".png", ".jpg", ".mp4"};

	/**
	 * 检查文件的合法性
	 * @param imgName 文件名称
	 */
	public static void checkFileEndWith(String imgName) {
		if (StringUtils.isBlank(imgName)) {
			throw new FileException(String.valueOf(HttpStatus.BAD_REQUEST), "文件名不能为空");
		}
		// 判断文件类型
		for (String pattern : legalPattern) {
			if (imgName.endsWith(pattern)) return;
		}
		throw new FileException(String.valueOf(HttpStatus.BAD_REQUEST), "文件名不合法");
	}
}
