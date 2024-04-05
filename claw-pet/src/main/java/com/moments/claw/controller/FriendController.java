package com.moments.claw.controller;

import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.FriendDtoPageQuery;
import com.moments.claw.domain.vo.FriendVo;
import com.moments.claw.service.FriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
