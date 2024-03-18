package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * (ClawOauth)表实体类
 *
 * @author chandler
 * @since 2024-03-18 21:28:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_oauth")
@ApiModel(value = "claw_oauth实体类",description = "用于存储传输claw_oauth信息")
public class Oauth extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "userId", value = "用户id（user表的id）")
    private Long userId;
        
    @ApiModelProperty(name = "oauthClient", value = "第三方平台名称")
    private String oauthClient;
        
    @ApiModelProperty(name = "oauthClientUserId", value = "第三方平台上的用户id")
    private String oauthClientUserId;
        
    @ApiModelProperty(name = "gender", value = "性别（0不详，1男生，2女生）")
    private Integer gender;
        
    @ApiModelProperty(name = "avatar", value = "头像url")
    private String avatar;
}
