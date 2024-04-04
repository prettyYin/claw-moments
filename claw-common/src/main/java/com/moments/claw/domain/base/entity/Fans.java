package com.moments.claw.domain.base.entity;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 用户粉丝表(ClawFans)表实体类
 *
 * @author chandler
 * @since 2024-04-05 01:29:48
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_fans")
@ApiModel(value = "claw_fans实体类",description = "用于存储传输claw_fans信息")
public class Fans extends BaseEntity {
    
    @MppMultiId
    @ApiModelProperty(name = "userId", value = "拥有粉丝的用户id", hidden = true)
    private Long userId;
    
    @MppMultiId
    @ApiModelProperty(name = "fansId", value = "粉丝的用户id", hidden = true)  
    private Long fansId;
}
