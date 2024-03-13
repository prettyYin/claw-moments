package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Notice;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 公告表(Notice)表控制层
 *
 * @author chandler
 * @since 2024-03-11 22:40:59
 */
@Api(tags = "NoticeController控制层", value = "/notice")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public R<?> selectAll() {
        return R.success(noticeService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("{id}")
    public R<?> selectOne(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        return R.success(this.noticeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param notice 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Notice notice) {
        return R.success(this.noticeService.save(notice));
    }

    /**
     * 修改数据
     *
     * @param notice 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Notice notice) {
        return R.success(this.noticeService.updateById(notice));
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
        return R.success(this.noticeService.removeByIds(idList));
    }
}

