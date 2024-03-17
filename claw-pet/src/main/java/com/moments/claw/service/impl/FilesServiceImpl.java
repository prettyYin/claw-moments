package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.mapper.FilesMapper;
import com.moments.claw.service.FilesService;
import org.springframework.stereotype.Service;

/**
 * (Files)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:35:08
 */
@Service("filesService")
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {

}

