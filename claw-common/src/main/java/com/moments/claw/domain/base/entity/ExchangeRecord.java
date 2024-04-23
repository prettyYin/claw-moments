package com.moments.claw.domain.base.entity;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 积分兑换商品记录表(ExchangeRecord)表实体类
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_exchange_record")
@ApiModel(value = "claw_exchange_record实体类",description = "用于存储传输claw_exchange_record信息")
public class ExchangeRecord extends BaseEntity {
    
    @MppMultiId
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(name = "itemId", value = "商品id", hidden = true)
    private Long itemId;
    
    @MppMultiId
    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(name = "userId", value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(name = "consumeScore", value = "消耗积分数")
    private Integer consumeScore;

    @NotNull(message = "商品json不能为空")
    @ApiModelProperty(name = "itemJson", value = "商品json数据")
    private String itemJson;

    @ApiModelProperty(name = "isPayload", value = "是否装备上（0否，1是）")
    private Integer isPayload;

    @ApiModelProperty(name = "consumeDate", value = "兑换时间")
    private LocalDate consumeDate;
}
