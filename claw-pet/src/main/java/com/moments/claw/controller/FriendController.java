package com.moments.claw.controller;

import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.FriendDtoPageQuery;
import com.moments.claw.domain.vo.FriendVo;
import com.moments.claw.service.FriendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

	@Resource
	private FriendService friendService;

	/**
	 * 互关列表
	 */
	@GetMapping("/list")
	public TableDataInfo<?> list(FriendDtoPageQuery pageQuery) {
		Long userId = SecurityUtils.getUserId();
		List<FriendVo> voList = friendService.friendList(userId);
		return PaginationUtil.handPaged(voList, pageQuery.getPageSize(), pageQuery.getPageNum());
	}

	/**
	 * 关注
	 */
	@GetMapping("/follow/{followId}")
	public R<?> follow(@PathVariable("followId") Long followId) {
		Long userId = SecurityUtils.getUserId();
		friendService.follow(userId,followId);
		return R.success("关注成功");
	}

	/**
	 * 取关
	 */
	@GetMapping("/unfollow/{followId}")
	public R<?> unfollow(@PathVariable("followId") Long followId) {
		Long userId = SecurityUtils.getUserId();
		friendService.unfollow(userId,followId);
		return R.success("取关成功");
	}
}
