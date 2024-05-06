package com.ruoyi.web.controller.system;

import com.moments.claw.domain.base.entity.Pet;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.service.FileUploadService;
import com.ruoyi.system.service.FilesService;
import com.ruoyi.system.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/system/pet")
@RequiredArgsConstructor
public class PetController extends BaseController {

    private final PetService petService;
    private final FileUploadService fileUploadService;
    private final FilesService filesService;

    @GetMapping(value = { "/", "/{petId}" })
    public AjaxResult getPet(@PathVariable("petId") String petId) {
        Pet pet = petService.selectPetById(petId);
        AjaxResult result = AjaxResult.success();
        result.put(AjaxResult.DATA_TAG, pet);
        return result;
    }

    @GetMapping("/list")
    public TableDataInfo list(Pet pet) {
        startPage();
        List<Pet> petList = petService.selectPetList(pet);
        return getDataTable(petList);
    }

    @PostMapping
    public AjaxResult insertPet(@RequestBody Pet pet) {
        return toAjax(petService.insertPet(pet));
    }

    @PutMapping
    public AjaxResult updatePet(@RequestBody Pet pet) {
        return toAjax(petService.updatePet(pet));
    }

    @DeleteMapping("/{petId}")
    public AjaxResult deletePet(@PathVariable("petId") String petId) {
        if (petId == null) {
            throw new ServiceException("请选择要删除的数据");
        }
        List<String> ids = Arrays.asList(petId.split(","));
        return toAjax(petService.deletePetById(ids));
    }

    @PostMapping("/uploadImg")
    public AjaxResult uploadImg(@RequestParam("file") MultipartFile file) {
        String fileId = fileUploadService.uploadImg(file);
        String furl = filesService.getFurl(fileId);
        AjaxResult result = new AjaxResult();
        return result.put(AjaxResult.DATA_TAG, furl);
    }
}