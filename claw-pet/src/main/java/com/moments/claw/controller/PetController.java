package com.moments.claw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moments.claw.domain.base.entity.Comments;
import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 宠物表(Pet)表控制层
 *
 * @author chandler
 * @since 2024-03-11 22:40:59
 */
@Api(tags = "PetController控制层", value = "/pet")
@RestController
@RequestMapping("/pet")
public class PetController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private PetService petService;
    @Autowired
    private FilesService filesService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentsService commentsService;

    /**
     * 赋值图片url
     */
    private void setImageUrl(Pet pet) {
        if(StringUtils.isNotBlank(pet.getImageIds())) {
            List<String> imageIds = Arrays.asList(pet.getImageIds().split(","));
            List<Files> files = filesService.listByIds(imageIds);
            List<String> imageUrls = files.stream().map(Files::getFileUrl).collect(Collectors.toList());
            pet.setImages(imageUrls);
        }
    }

    private void setArticle(Pet pet) {
        if (Objects.nonNull(pet.getArticleId())) {
            pet.setArticle(articleService.getById(pet.getArticleId()));
        }
    }

    private void setMember(Pet pet) {
        if (Objects.nonNull(pet.getMemberId())) {
            pet.setMember(memberService.getById(pet.getMemberId()));
        }
    }

    private void setComments(Pet pet) {
        List<Comments> rootComments = commentsService.getRootComments();
        List<String> res = rootComments.stream().map(Comments::getContent).collect(Collectors.toList());
        pet.setComments(res);
    }

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll(Pet pet) {
        startPage();
        List<Pet> list = petService.list(new QueryWrapper<>(pet));
        list.stream().forEach(p -> setImageUrl(p));
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
    public R<?> selectOne(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        Pet pet = this.petService.getById(id);
        setImageUrl(pet);
        setArticle(pet);
        setMember(pet);
        setComments(pet);
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

