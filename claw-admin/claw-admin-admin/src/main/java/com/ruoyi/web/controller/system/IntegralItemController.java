package com.ruoyi.web.controller.system;

import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.service.FileUploadService;
import com.ruoyi.system.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/system/inegral")
@RequiredArgsConstructor
public class IntegralItemController extends BaseController {

    private final IntegralItemService integralItemService;
    private final FileUploadService fileUploadService;

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

    @PostMapping("/uploadImg")
    public AjaxResult uploadImg(@RequestParam("file") MultipartFile file) {
        Files successFile = fileUploadService.uploadFileToDirectory(file, "mall");
        AjaxResult ajax = success();
        ajax.put(AjaxResult.DATA_TAG, successFile.getFileUrl());
        return ajax;
    }

    /**
     * 新增
     */
    @PostMapping
    public AjaxResult addIntegralItem(@RequestBody IntegralItem integralItem) {
        integralItemService.insertIntegralItem(integralItem);
        return success();
    }

    /**
     * 修改
     */
    @PutMapping
    public AjaxResult updateIntegralItem(@RequestBody IntegralItem integralItem) {
        integralItemService.updateIntegralItem(integralItem);
        return success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteIntegralItem(@PathVariable("id") String id) {
        if (id == null) {
            throw new ServiceException("请选择要删除的积分商品");
        }
        List<String> ids = Arrays.asList(id.split(","));
        integralItemService.deleteIntegralItem(ids);
        return success();
    }
}
