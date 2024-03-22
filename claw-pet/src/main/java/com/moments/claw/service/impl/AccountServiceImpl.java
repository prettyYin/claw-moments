package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Account;
import com.moments.claw.mapper.AccountMapper;
import com.moments.claw.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * (Account)表服务实现类
 *
 * @author chandler
 * @since 2024-03-19 22:56:34
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}

