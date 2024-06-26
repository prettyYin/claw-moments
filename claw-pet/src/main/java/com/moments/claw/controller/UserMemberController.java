package com.moments.claw.controller;


import com.moments.claw.domain.base.entity.UserMember;
import com.moments.claw.service.UserMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户/会员关联表(ClawUserMember)表控制层
 *
 * @author chandler
 * @since 2024-04-22 15:03:34
 */
@Api(tags = "UserMemberController控制层", value = "/userMember")
@RestController
@RequiredArgsConstructor
@RequestMapping("/userMember")
public class UserMemberController extends BaseController {
    /**
     * 服务对象
     */
    private final UserMemberService userMemberService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        startPage();
        return getDataTable(userMemberService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("/{id}")
    public R<?> selectOne(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        return R.success(userMemberService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userMember 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody UserMember userMember) {
        return R.success(userMemberService.save(userMember));
    }

    /**
     * 修改数据
     *
     * @param userMember 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody UserMember userMember) {
        return R.success(userMemberService.updateByMultiId(userMember));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping
    public R<?> delete(@ApiParam(name = "idList", value = "id数组", required = true) @RequestParam("idList") List<Long> idList) {
        return R.success(userMemberService.removeByIds(idList));
    }
}

