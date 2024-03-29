package com.moments.claw.utils;

import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	private static String[] legalPattern = {".png", ".jpg", ".mp4"};

	/**
	 * 检查文件的合法性
	 * @param imgName 文件名称
	 */
	public static void checkFileEndWith(String imgName) {
		if (StringUtils.isBlank(imgName)) {
			throw new BizException(ResultEnum.NOT_FILE_NAME);
		}
		// 判断文件类型
		for (String pattern : legalPattern) {
			if (imgName.endsWith(pattern)) return;
		}
		throw new BizException(ResultEnum.FILE_TYPE_ERROR);
	}
}
