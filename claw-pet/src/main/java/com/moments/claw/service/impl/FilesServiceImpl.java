package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.mapper.FilesMapper;
import com.moments.claw.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Files)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:35:08
 */
@Service("filesService")
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {


	@Override
	public List<Files> listByFileIds(List<String> imageIds) {
		List<Files> files = null;
		if (Objects.nonNull(imageIds) && imageIds.size() > 0) {
			files = list(new LambdaUpdateWrapper<Files>().in(Files::getFileId, imageIds));
		}
		return files;
	}

	@Override
	public String getFurl(String id) {
		if (id == null) {
			return null;
		}
		Files file = getById(id);
		if (Objects.isNull(file)) {
			return null;
		}
		return file.getFileUrl();
	}

	@Override
	public List<String> getFurlBatch(String[] ids) {
		ArrayList<String> furls = new ArrayList<>();
		for (String id : ids) {
			String furl = getFurl(id);
			furls.add(furl);
		}
		return furls;
	}
}

