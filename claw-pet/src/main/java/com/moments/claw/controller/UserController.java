package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (ClawUser)表控制层
 *
 * @author chandler
 * @since 2024-03-18 21:33:01
 */
@Api(tags = "UserController控制层", value = "/user")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @ApiOperation(value = "获取登陆人信息")
    @GetMapping("/index")
    public R<?> index() {
        Long id = SecurityUtils.getUserId();
        User user = userService.getUserInfoById(id);
        return R.success(user);
    }

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        startPage();
        return getDataTable(userService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("/index/{id}")
    public R<?> selectOne(@ApiParam(name = "id", value = "id", required = true) @PathVariable Long id) {
        return R.success(userService.getUserInfoById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody User user) {
        return R.success(userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping("/update")
    public R<?> update(@RequestBody User user) {
        return R.success(userService.updateById(user));
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
        return R.success(userService.removeByIds(idList));
    }

    /**
     * 获取用户的头像地址
     * @param userId 用户id
     * @return 头像地址
     */
    @GetMapping("/getFileUrl/{userId}")
    public R<?> getFileUrlByUserId(@PathVariable("userId") Long userId) {
        return R.success(userService.getFileUrlByUserId(userId));
    }

    /**
     * 获取昵称
     * @param userId 用户id
     * @return 昵称
     */
    @GetMapping("/getNickname/{userId}")
    public R<?> getNicknameByUserId(@PathVariable("userId") Long userId) {
        return R.success(userService.getNicknameByUserId(userId));
    }

    /**
     * 根据昵称模糊查找用户
     * @param nickname 用户昵称
     * @return 用户信息
     */
    @GetMapping("/searchUser")
    public R<?> searchUser(@RequestParam("nickname") String nickname) {
        return R.success(userService.searchUserLikeNickname(nickname));
    }

    /**
     * 设为管理员权限
     */
    @PreAuthorize("@perms.hasAuthority('admin:admin')")
    @PostMapping("/setAdmin/{userId}")
    public R<?> setAdmin(@PathVariable Long userId) {
        userService.setAdmin(userId);
        return R.success();
    }

    /**
     * 取消管理员权限
     */
    @PreAuthorize("@perms.hasAuthority('admin:admin')")
    @PostMapping("/cancelAdmin/{userId}")
    public R<?> cancelAdmin(@PathVariable Long userId) {
        userService.cancelAdmin(userId);
        return R.success();
    }
}

