package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Files)表数据库访问层
 *
 * @author chandler
 * @since 2024-03-17 17:36:09
 */
@Mapper
public interface FilesMapper extends BaseMapper<Files> {

	void insertFile(Files file);

	Files getById(@Param("id") String id);

	List<Files> listInImageIds(List<String> imageIds);
}
