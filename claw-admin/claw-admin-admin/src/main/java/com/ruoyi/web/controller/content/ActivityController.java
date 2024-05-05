package com.ruoyi.web.controller.content;

import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.Article;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ActivityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活动表(Activities)表控制层
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Api(tags = "ActivityController控制层", value = "/activity")
@RestController
@RequestMapping("/content/activity")
@RequiredArgsConstructor
public class ActivityController extends BaseController {

    private final ActivityService activityService;

    @GetMapping("/list")
    public TableDataInfo list(Activity activity) {
        startPage();
        List<Article> list = activityService.selectList(activity);
        return getDataTable(list);
    }
}