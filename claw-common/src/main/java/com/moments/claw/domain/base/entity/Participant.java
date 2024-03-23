package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moments.claw.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (ClawParticipants)表实体类
 *
 * @author chandler
 * @since 2024-03-23 21:46:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_participant")
@ApiModel(value = "claw_participant实体类",description = "用于存储传输claw_participant信息")
public class Participant extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "userId", value = "用户唯一标识符，关联用户表")
    private Long userId;
        
    @ApiModelProperty(name = "activityId", value = "活动唯一标识符，关联活动表")
    private Long activityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "registrationDate", value = "报名时间")
    private Date registrationDate;
        
    @ApiModelProperty(name = "status", value = "状态（1审核中，2已审核，3已拒绝，4已取消）")
    private Integer status;
        
    @ApiModelProperty(name = "remark", value = "审核备注或取消原因")
    private String remark;
}
