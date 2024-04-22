package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * (Member)表实体类
 *
 * @author chandler
 * @since 2024-04-14 18:01:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_member")
@ApiModel(value = "claw_member实体类",description = "用于存储传输claw_member信息")
public class Member extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "memberId", value = "会员id", hidden = true)
    private Long memberId;
    
    @ApiModelProperty(name = "name", value = "会员名称")
    private String name;
        
    @ApiModelProperty(name = "icon", value = "会员图标")
    private String icon;
        
    @ApiModelProperty(name = "level", value = "会员等级（0：非会员；1：大众会员；2：白银会员；3：黄金会员；4：铂金会员；5：钻石会员；6：尊贵会员；7：豪华会员）")
    private Integer level;

}
