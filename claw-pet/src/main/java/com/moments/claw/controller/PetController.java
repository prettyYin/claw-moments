package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 宠物表(Pet)表控制层
 *
 * @author chandler
 * @since 2024-03-11 22:40:59
 */
@Api(tags = "PetController控制层", value = "/pet")
@RestController
@RequestMapping("/pet")
@Slf4j
public class PetController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private PetService petService;
    @Resource
    private RedisService redisService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll(Pet pet) {
        startPage();
        List<Pet> list = petService.selectAll(pet);
        redisService.set("test", "test123");
        Object redisTest = redisService.get("test");
        log.info("redis test info {}", redisTest);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("/view/{id}")
    public R<?> viewDetailById(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        Pet pet = this.petService.viewDetailById(id);
        return R.success(pet);
    }

    /**
     * 新增数据
     *
     * @param pet 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Pet pet) {
        return R.success(this.petService.save(pet));
    }

    /**
     * 修改数据
     *
     * @param pet 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Pet pet) {
        return R.success(this.petService.updateById(pet));
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
        return R.success(this.petService.removeByIds(idList));
    }
}

