package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 标签表(Tags)表实体类
 *
 * @author chandler
 * @since 2024-03-17 20:45:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_tags")
@ApiModel(value = "claw_tags实体类",description = "用于存储传输claw_tags信息")
public class Tags extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "userId", value = "用户id")
    private Long userId;
        
    @ApiModelProperty(name = "memberId", value = "会员id")
    private Long memberId;
        
    @ApiModelProperty(name = "petId", value = "宠物id")
    private Long petId;

    @ApiModelProperty(name = "tagKey", value = "标签标识符")
    private String tagKey;

    @ApiModelProperty(name = "tagName", value = "标签名")
    private String tagName;
        
    @ApiModelProperty(name = "tagType", value = "标签类型")
    private Integer tagType;
        
    @ApiModelProperty(name = "status", value = "状态（-1异常，1发布，2草稿，3禁用）")
    private Integer status;
        
}
