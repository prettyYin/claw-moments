package com.ruoyi.web.controller.system;

import com.moments.claw.domain.base.entity.IntegralItem;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/inegral")
@RequiredArgsConstructor
public class IntegralItemController extends BaseController {

    private final IntegralItemService integralItemService;
    @GetMapping("/list")
    public TableDataInfo integralItemList(IntegralItem integralItem) {
        startPage();
        return getDataTable(integralItemService.selectList(integralItem));
    }
}
