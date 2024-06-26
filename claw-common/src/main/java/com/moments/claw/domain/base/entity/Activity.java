package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.moments.claw.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 活动表(ClawActivities)表实体类
 *
 * @author chandler
 * @since 2024-03-23 21:46:34
 */
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_activity")
@ApiModel(value = "claw_activity实体类",description = "用于存储传输claw_activity信息")
public class Activity extends BaseEntity {
    
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;

    @ApiModelProperty(name = "publishUserId", value = "发布人id")
    private Long publishUserId;

    @ApiModelProperty(name = "title", value = "活动标题")
    private String title;
        
    @ApiModelProperty(name = "content", value = "活动详细描述")
    private String content;

    @ApiModelProperty(name = "imageIds", value = "图片ids")
    private String imageIds;

    @ApiModelProperty(name = "thumbCount", value = "点赞数")
    private Long thumbCount;

    @ApiModelProperty(name = "viewCount", value = "浏览数")
    private Long viewCount;

    @ApiModelProperty(name = "participantCount", value = "活动参与人数")
    private Long participantCount;

    @ApiModelProperty(name = "surplusCount", value = "活动剩余人数")
    private Long surplusCount;

    @ApiModelProperty(name = "capacity", value = "活动最大参与人数(0表示不限制人数)")
    private Long capacity;

    @ApiModelProperty(name = "status", value = "状态（-1异常，1发布，2草稿）")
    private Integer status;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "startTime", value = "活动开始时间")
    private LocalDateTime startTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "endTime", value = "活动结束时间")
    private LocalDateTime endTime;

    @TableField(exist = false)
    @ApiModelProperty(name = "coverImageUrl", value = "活动封面图片URL")
    private String coverImageUrl;

    @TableField(exist = false)
    @ApiModelProperty(name = "isMyPublish", value = "是否本人发布的活动")
    private Boolean isMyPublish;
}
