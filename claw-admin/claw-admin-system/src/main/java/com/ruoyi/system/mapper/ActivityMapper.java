package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动表(Activities)表数据库访问层
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<Article> selectList(Activity activity);

    void deleteActivityById(@Param("ids") List<String> id);

    void insertActivity(Activity activity);

    Activity getActivityById(@Param("id") String id);
}