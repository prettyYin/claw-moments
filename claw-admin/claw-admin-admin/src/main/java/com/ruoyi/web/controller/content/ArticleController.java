package com.ruoyi.web.controller.content;

import com.moments.claw.domain.base.entity.Article;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ArticleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 宠物表(Pet)表控制层
 *
 * @author chandler
 * @since 2024-03-11 22:40:59
 */
@Api(tags = "ArticleController控制层", value = "/article")
@RestController
@RequiredArgsConstructor
@RequestMapping("/content/article")
@Slf4j
public class ArticleController extends BaseController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public TableDataInfo list(Article article) {
        startPage();
        List<Article> list = articleService.selectList(article);
        return getDataTable(list);
    }
}