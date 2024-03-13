package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Notice;
import com.moments.claw.mapper.NoticeMapper;
import com.moments.claw.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * 公告表(Notice)表服务实现类
 *
 * @author chandler
 * @since 2024-03-11 22:19:52
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}

