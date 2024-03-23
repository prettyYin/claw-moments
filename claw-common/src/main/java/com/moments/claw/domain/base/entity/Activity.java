package com.moments.claw.domain.base.entity;

import java.util.Date;
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

/**
 * 活动表(ClawActivities)表实体类
 *
 * @author chandler
 * @since 2024-03-23 21:46:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_activity")
@ApiModel(value = "claw_activity实体类",description = "用于存储传输claw_activity信息")
public class Activity extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "title", value = "活动标题")
    private String title;
        
    @ApiModelProperty(name = "content", value = "活动详细描述")
    private String content;
        
    @ApiModelProperty(name = "coverImageId", value = "活动封面图片id")
    private Long coverImageId;

    @ApiModelProperty(name = "coverImageUrl", value = "活动封面图片URL")
    private String coverImageUrl;

    @ApiModelProperty(name = "capacity", value = "活动最大参与人数(0表示不限制人数)")
    private Integer capacity;

    @ApiModelProperty(name = "status", value = "状态（-1异常，1发布，2草稿）")
    private Integer status;
}
