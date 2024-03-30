package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Files;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.service.FileUploadService;
import com.moments.claw.service.FilesService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequiredArgsConstructor
@RequestMapping("/file")
public class FilesController extends BaseController {

    private final FilesService filesService;

    private final FileUploadService fileUploadService;

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
        return R.success(filesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param files 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Files files) {
        return R.success(filesService.save(files));
    }

    /**
     * 修改数据
     *
     * @param files 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Files files) {
        return R.success(filesService.updateById(files));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return R.success(filesService.removeByIds(idList));
    }

    /**
     * 上传文件
     */
    @PostMapping("/images")
    public R<?> uploadImages(@RequestPart MultipartFile file) {
        String fileId = fileUploadService.uploadImg(file);
        return R.success(fileId);
    }

    /**
     * 获取文件url
     */
    @GetMapping("/getUrl")
    public R<?> getFurl(@PathVariable String id) {
        return R.success(filesService.getFurl(id));
    }
}

