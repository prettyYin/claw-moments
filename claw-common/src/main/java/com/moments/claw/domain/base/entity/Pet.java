package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moments.claw.domain.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 宠物信息表
 * @TableName claw_pet
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value ="claw_pet")
@Data
public class Pet extends BaseEntity {
    /**
     * 宠物id
     */
    @TableId
    private Long petId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 宠物昵称
     */
    private String petName;

    /**
     *  宠物介绍
     */
    private String petDesc;

    /**
     * 宠物照片
     */
    private String imgUrl;

    /**
     * 品种
     */
    private String category;

    /**
     * 性别（0男1女）
     */
    private Integer gender;

    /**
     * 出生年月
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 主人昵称
     */
    @TableField(exist = false)
    private String nickname;
}