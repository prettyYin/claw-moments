package com.moments.claw.domain.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.moments.claw.domain.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MabatisPlus 自动填充内容
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//表示是自己创建
        }
        this.setFieldValByName("created_at", new Date(), metaObject);
        this.setFieldValByName("created_by",userId , metaObject);
        this.setFieldValByName("updated_at", new Date(), metaObject);
        this.setFieldValByName("updated_by", userId, metaObject);
        this.setFieldValByName("parent_id", -1L, metaObject); // 分类默认父id为-1
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated_at", new Date(), metaObject);
        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}