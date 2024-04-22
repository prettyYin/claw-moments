package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.SignRecord;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.SignRecordDto;
import com.moments.claw.service.SignRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;

import java.time.LocalDate;

/**
 * 用户签到表(SignRecord)表控制层
 *
 * @author chandler
 * @since 2024-04-22 15:43:15
 */
@Api(tags = "SignRecordController控制层", value = "/signRecord")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignRecordController extends BaseController {
    /**
     * 服务对象
     */
    private final SignRecordService signRecordService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        startPage();
        return getDataTable(signRecordService.list());
    }

    /**
     * 今日签到
     * @return 连续签到天数
     */
    @ApiOperation(value = "新增数据")
    @PostMapping("/signToday")
    public R<?> signToday(@RequestBody SignRecordDto dto) {
        return R.success(signRecordService.signToday(dto.getIntegral()));
    }

    /**
     * 获取连续签到天数
     * @return 连续签到天数
     */
    @GetMapping("/signDays")
    public R<?> getConsecutiveSignDays() {
        Long userId = SecurityUtils.getUserId();
        LocalDate today = LocalDate.now(); // 今天
        Integer signDays = 0;
        SignRecord todaySignRecord = signRecordService.getSignRecordDate(userId, today);
        if (todaySignRecord != null) {
            signDays++;
        }
        LocalDate beforeDay = today.minusDays(1); // 前一天
        return R.success(signRecordService.getConsecutiveSignDays(userId, beforeDay, signDays));
    }

    /**
     * 今日是否已签到
     * @return 今日签到结果
     */
    @GetMapping("/isSignedToday")
    public R<?> isSignedToday() {
        return R.success(signRecordService.isSignedToday());
    }

    /**
     * 修改数据
     *
     * @param signRecord 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody SignRecord signRecord) {
        return R.success(signRecordService.updateById(signRecord));
    }
}

