package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.dto.MobileDto;

import java.util.List;


/**
 * (ClawUser)表服务接口
 *
 * @author chandler
 * @since 2024-03-18 21:33:01
 */
public interface UserService extends IService<User> {

	User getByMobile(String mobile);

	User getByUsername(String username);

	String smsCode(MobileDto mobileDto);

	User getUserInfoById(Long id);

	String getFileUrlByUserId(Long userId);

	String getNicknameByUserId(Long userId);

	List<User> searchUserLikeNickname(String nickname);
}
