package com.moments.claw.domain.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

import java.util.Date;

/**
 * 广告表(Advertise)表实体类
 *
 * @author chandler
 * @since 2024-03-11 22:03:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_advertise")
@ApiModel(value = "claw_advertise实体类",description = "用于存储传输claw_advertise信息")
public class Advertise extends BaseEntity {
    //主键    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "merchantId", value = "商品id")
    private Long merchantId;
        
    @ApiModelProperty(name = "name", value = "广告名")
    private String name;
        
    @ApiModelProperty(name = "cover", value = "广告路径")
    private String cover;
        
    @ApiModelProperty(name = "location", value = "页面位置")
    private String location;
        
    @ApiModelProperty(name = "view", value = "点击次数")
    private Long view;
        
    @ApiModelProperty(name = "jumpLink", value = "跳转链接")
    private String jumpLink;
        
    @ApiModelProperty(name = "jumpType", value = "跳转类型")
    private Integer jumpType;
        
    @ApiModelProperty(name = "extendLink", value = "继承链接")
    private String extendLink;
        
    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;
        
    @ApiModelProperty(name = "status", value = "状态（0异常，1正常）")
    private Integer status;
}
