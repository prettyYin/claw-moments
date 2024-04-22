package com.moments.claw.domain.base.entity;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 用户/会员关联表(ClawUserMember)表实体类
 *
 * @author chandler
 * @since 2024-04-22 15:03:34
 */
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_user_member")
@ApiModel(value = "claw_user_member实体类",description = "用于存储传输claw_user_member信息")
public class UserMember extends BaseEntity {
    
    @MppMultiId
    @ApiModelProperty(name = "userId", value = "用户id", hidden = true)
    private Long userId;
    
    @MppMultiId
    @ApiModelProperty(name = "memberId", value = "会员id", hidden = true)  
    private Long memberId;
        
    @ApiModelProperty(name = "cardId", value = "身份证")
    private String cardId;
        
    @ApiModelProperty(name = "phoneNumber", value = "手机号")
    private String phoneNumber;
        
    @ApiModelProperty(name = "balance", value = "余额")
    private Double balance;
        
    @ApiModelProperty(name = "integral", value = "积分")
    private Long integral;
        
    @ApiModelProperty(name = "preference", value = "已优惠金额")
    private Double preference;
        
    @ApiModelProperty(name = "status", value = "状态（-1异常，1正常，2冻结，3注销）")
    private Integer status;
        
}
