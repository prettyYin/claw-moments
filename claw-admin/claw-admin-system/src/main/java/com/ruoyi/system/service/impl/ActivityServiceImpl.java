package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.Article;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.service.ActivityService;
import com.ruoyi.system.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动表(Activities)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    private final ActivityMapper activityMapper;
    private final FilesService filesService;

    @Override
    public List<Article> selectList(Activity activity) {
        return activityMapper.selectList(activity);
    }

    @Override
    public void deleteActivityByIds(List<String>  ids) {
        activityMapper.deleteActivityById(ids);
    }

    @Override
    public void insertActivity(Activity activity) {
        activityMapper.insertActivity(activity);
    }

    @Override
    public Activity getActivityById(String id) {
        Activity activity = activityMapper.getActivityById(id);
        if (activity.getImageIds() != null) {
            String[] ids = activity.getImageIds().split(",");
            List<String> images = filesService.getFurlBatch(ids);
            activity.setCoverImageUrl(images.get(0));
        }
        return activity;
    }
}