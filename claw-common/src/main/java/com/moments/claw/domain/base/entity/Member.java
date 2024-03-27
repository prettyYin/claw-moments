package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * (Member)表实体类
 *
 * @author chandler
 * @since 2024-03-11 22:03:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_member")
@ApiModel(value = "claw_member实体类",description = "用于存储传输claw_member信息")
public class Member extends BaseEntity {
    //会员id    
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "会员id", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "name", value = "会员名称")
    private String name;

    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;
        
    @ApiModelProperty(name = "level", value = "会员等级（0：非会员；1：大众会员；2：白银会员；3：黄金会员；4：铂金会员；5：钻石会员；6：尊贵会员；7：豪华会员）")
    private Integer level;
        
    @ApiModelProperty(name = "cardId", value = "身份证")
    private String cardId;
        
    @ApiModelProperty(name = "phoneNumber", value = "手机号")
    private String phoneNumber;
        
    @ApiModelProperty(name = "discount", value = "折扣率（如0.85）")
    private Double discount;
        
    @ApiModelProperty(name = "icon", value = "会员图标")
    private String icon;
        
    @ApiModelProperty(name = "balance", value = "余额")
    private Double balance;
        
    @ApiModelProperty(name = "integral", value = "积分")
    private Long integral;
        
    @ApiModelProperty(name = "preference", value = "已优惠金额")
    private Double preference;
        
    @ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
    private Integer status;
}
