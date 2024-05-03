package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Files;
import com.ruoyi.system.mapper.FilesMapper;
import com.ruoyi.system.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Files)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:35:08
 */
@Service("adminFilesService")
@RequiredArgsConstructor
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {

	private final FilesMapper filesMapper;

	@Override
	public List<Files> listByFileIds(List<String> imageIds) {
		List<Files> files = null;
		if (Objects.nonNull(imageIds) && imageIds.size() > 0) {
//			files = list(new LambdaUpdateWrapper<Files>().in(Files::getFileId, imageIds));
			files = filesMapper.listInImageIds(imageIds);
		}
		return files;
	}

	@Override
	public String getFurl(String id) {
		if (id == null) {
			return null;
		}
		Files file = filesMapper.getById(id);
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

	@Override
	public void insertFile(Files file) {
		filesMapper.insertFile(file);
	}
}

