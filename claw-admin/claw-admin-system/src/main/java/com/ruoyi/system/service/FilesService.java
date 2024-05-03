package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Files;

import java.io.Serializable;
import java.util.List;


/**
 * (Files)表服务接口
 *
 * @author chandler
 * @since 2024-03-17 17:35:08
 */
public interface FilesService extends IService<Files> {


	default LambdaQueryChainWrapper<Files> select(SFunction<Files, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	List<Files> listByFileIds(List<String> imageIds);

	/**
	 * 获取图片url
	 */
	String getFurl(String id);

	/**
	 * 批量获取图片url
	 */
	List<String> getFurlBatch(String[] ids);

	void insertFile(Files file);
}
