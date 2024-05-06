package com.ruoyi.web.controller.system;

import com.moments.claw.domain.base.entity.IntegralItem;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/inegral")
@RequiredArgsConstructor
public class IntegralItemController extends BaseController {

    private final IntegralItemService integralItemService;

    @GetMapping(value = { "/", "/{intergralId}" })
    public AjaxResult getInfo(@PathVariable(value = "intergralId", required = false) Long integralId) {
        IntegralItem item = integralItemService.selectIntegralById(integralId);
        AjaxResult result = AjaxResult.success();
        result.put(AjaxResult.DATA_TAG, item);
        return result;
    }

    @GetMapping("/list")
    public TableDataInfo integralItemList(IntegralItem integralItem) {
        startPage();
        return getDataTable(integralItemService.selectList(integralItem));
    }
}
