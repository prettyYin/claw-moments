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
 * (ClawAccount)表实体类
 *
 * @author chandler
 * @since 2024-03-19 22:54:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_account")
@ApiModel(value = "claw_account实体类",description = "用于存储传输claw_account信息")
public class Account extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "merchantId", value = "商户")
    private Long merchantId;
        
    @ApiModelProperty(name = "storeId", value = "物品id")
    private Long storeId;
        
    @ApiModelProperty(name = "memberId", value = "会员id")
    private Long memberId;
        
    @ApiModelProperty(name = "memberType", value = "会员类型（与member表的level关联）")
    private Integer memberType;
        
    @ApiModelProperty(name = "userMoney", value = "用户余额总数")
    private Double userMoney;
        
    @ApiModelProperty(name = "accumulateMoney", value = "累计余额总数")
    private Double accumulateMoney;
        
    @ApiModelProperty(name = "giveMoney", value = "捐赠钱款总数")
    private Double giveMoney;
        
    @ApiModelProperty(name = "consumeMoney", value = "消费钱款总数")
    private Double consumeMoney;
        
    @ApiModelProperty(name = "frozenMoney", value = "冻结余额总数")
    private Double frozenMoney;
        
    @ApiModelProperty(name = "userIntegral", value = "用户积分总数")
    private Long userIntegral;
        
    @ApiModelProperty(name = "accumulateIntegral", value = "累计积分总数")
    private Long accumulateIntegral;
        
    @ApiModelProperty(name = "giveIntegral", value = "捐赠积分总数")
    private Long giveIntegral;
        
    @ApiModelProperty(name = "consumeIntegral", value = "消耗积分总数")
    private Long consumeIntegral;
        
    @ApiModelProperty(name = "frozenIntegral", value = "冻结积分")
    private Long frozenIntegral;
        
    @ApiModelProperty(name = "userGrowth", value = "用户成长值")
    private Long userGrowth;
        
    @ApiModelProperty(name = "accumulateDrawnMoney", value = "累计提现金额")
    private Double accumulateDrawnMoney;

    @ApiModelProperty(name = "status", value = "状态（-1异常，1正常，2禁用）")
    private Integer status;
}
