package com.moments.claw.domain.base.entity;

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
 * 活动、用户关联表(ActivityUser)表实体类
 *
 * @author chandler
 * @since 2024-03-24 20:54:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_activity_user")
@ApiModel(value = "claw_activity_user实体类",description = "用于存储传输claw_activity_user信息")
public class ActivityUser extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "activityId", value = "活动id（活动表id）", hidden = true)
    private Long activityId;
        
    @ApiModelProperty(name = "userId", value = "用户id（用户表id）")
    private Long userId;
        
    @ApiModelProperty(name = "type", value = "活动类型（1已报名，2审核中，3已取消）")
    private Integer type;
        
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}
