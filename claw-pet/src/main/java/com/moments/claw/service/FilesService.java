package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Files;

import java.util.List;


/**
 * (Files)表服务接口
 *
 * @author chandler
 * @since 2024-03-17 17:35:08
 */
public interface FilesService extends IService<Files> {

	List<Files> listByFileIds(List<String> imageIds);
}
