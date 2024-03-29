package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 活动、用户关联表(ActivityUser)表实体类
 *
 * @author chandler
 * @since 2024-03-24 20:54:51
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_activity_user")
@ApiModel(value = "claw_activity_user实体类",description = "用于存储传输claw_activity_user信息")
public class ActivityUser extends BaseEntity {

    @MppMultiId
    @ApiModelProperty(name = "activityId", value = "活动id（活动表id）", hidden = true)
    @NotNull(message = "活动id不能为空")
    private Long activityId;

    @MppMultiId
    @ApiModelProperty(name = "userId", value = "用户id（用户表id）")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(name = "type", value = "活动类型（1已报名，2审核中，3已通过，4未报名）")
    private Integer type;

    @ApiModelProperty(name = "thumbStatus", value = "点赞状态（1已经赞，2未点赞）")
    private Integer thumbStatus;

}
