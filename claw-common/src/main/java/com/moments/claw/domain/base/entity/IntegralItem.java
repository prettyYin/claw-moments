package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 积分商品表(IntegralItem)表实体类
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
@TableName("claw_integral_item")
@ApiModel(value = "claw_integral_item实体类",description = "用于存储传输claw_integral_item信息")
public class IntegralItem extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "goodsDesc", value = "商品描述")
    private String goodsDesc;
        
    @ApiModelProperty(name = "goodsUrl", value = "商品链接")
    private String goodsUrl;
        
    @ApiModelProperty(name = "goodsScore", value = "商品积分")
    private Integer goodsScore;
}
