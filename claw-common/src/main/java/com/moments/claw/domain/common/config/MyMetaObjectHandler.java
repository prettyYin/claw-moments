package com.moments.claw.domain.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.moments.claw.domain.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MabatisPlus 自动填充内容
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String userName = null;
        try {
            userName = SecurityUtils.getUsername();
        } catch (Exception e) {
            e.printStackTrace();
            userName = "self";//表示是自己创建
        }
        this.setFieldValByName("createdAt", new Date(), metaObject);
        this.setFieldValByName("createdBy",userName , metaObject);
        this.setFieldValByName("updatedAt", new Date(), metaObject);
        this.setFieldValByName("updatedBy", userName, metaObject);
        this.setFieldValByName("parentId", -1L, metaObject); // 分类默认父id为-1
        this.setFieldValByName("sendTime", LocalDateTime.now(), metaObject); // 分类默认父id为-1
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated_at", new Date(), metaObject);
        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}