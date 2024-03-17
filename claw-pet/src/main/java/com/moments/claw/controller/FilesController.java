package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.service.FilesService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (ClawFiles)表控制层
 *
 * @author chandler
 * @since 2024-03-17 17:32:44
 */
@Api(tags = "")
@RestController
@RequestMapping("clawFiles")
public class FilesController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private FilesService filesService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        return getDataTable(filesService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return R.success(this.filesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param files 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Files files) {
        return R.success(this.filesService.save(files));
    }

    /**
     * 修改数据
     *
     * @param files 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Files files) {
        return R.success(this.filesService.updateById(files));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return R.success(this.filesService.removeByIds(idList));
    }
}

