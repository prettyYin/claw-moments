package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController extends BaseController {

    private final PetService petService;

    @GetMapping("/list")
    public R<?> list() {
        Long userId = SecurityUtils.getUserId();
        return R.success(petService.listMyPets(userId));
    }

    @PostMapping
    public R<?> add(@RequestBody Pet pet) {
        if (pet.getUserId() == null) {
            pet.setUserId(SecurityUtils.getUserId());
        }
        petService.save(pet);
        return R.success();
    }

    @PutMapping
    public R<?> update(@RequestBody Pet pet) {
        petService.updateById(pet);
        return R.success();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        petService.removeById(id);
        return R.success();
    }

    @GetMapping("/getPet/{petId}")
    public R<?> myPetList(@PathVariable("petId") Long petId) {
        return R.success(petService.getById(petId));
    }
}
