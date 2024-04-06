package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * 用户关注表(ClawFollow)表实体类
 *
 * @author chandler
 * @since 2024-04-05 01:29:49
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_follow")
@ApiModel(value = "claw_follow实体类",description = "用于存储传输claw_follow信息")
public class Follow {
    
    @MppMultiId
    @ApiModelProperty(name = "userId", value = "关注者的用户id", hidden = true)
    private Long userId;
    
    @MppMultiId
    @ApiModelProperty(name = "followId", value = "被关注者的用户id", hidden = true)  
    private Long followId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updatedAt;

    @ApiModelProperty(value = "备注")
    private String remark;
}
