package com.moments.claw.domain.base.entity;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 用户关注表(ClawFollow)表实体类
 *
 * @author chandler
 * @since 2024-04-05 01:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_follow")
@ApiModel(value = "claw_follow实体类",description = "用于存储传输claw_follow信息")
public class Follow extends BaseEntity {
    
    @MppMultiId
    @ApiModelProperty(name = "userId", value = "关注者的用户id", hidden = true)
    private Long userId;
    
    @MppMultiId
    @ApiModelProperty(name = "followId", value = "被关注者的用户id", hidden = true)  
    private Long followId;
}
