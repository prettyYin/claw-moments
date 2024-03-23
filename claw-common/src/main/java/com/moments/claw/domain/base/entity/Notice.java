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
 * 公告表(Notice)表实体类
 *
 * @author chandler
 * @since 2024-03-11 22:03:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_notice")
@ApiModel(value = "claw_notice实体类",description = "用于存储传输claw_notice信息")
public class Notice extends BaseEntity {
    //主键    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "memberId", value = "会员id")
    private Long memberId;
        
    @ApiModelProperty(name = "merchantId", value = "商品id")
    private Long merchantId;
        
    @ApiModelProperty(name = "title", value = "商品标题")
    private String title;
        
    @ApiModelProperty(name = "content", value = "商品内容（html格式）")
    private String content;
        
    @ApiModelProperty(name = "cover", value = "路径")
    private String cover;
        
    @ApiModelProperty(name = "synopsis", value = "摘要")
    private String synopsis;
        
    @ApiModelProperty(name = "view", value = "点击次数")
    private Long view;
        
    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;
        
    @ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "是否永久有效（0不是，1是）")
    private Integer isPermanent;
}
