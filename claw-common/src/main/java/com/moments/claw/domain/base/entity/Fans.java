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
 * 用户粉丝表(ClawFans)表实体类
 *
 * @author chandler
 * @since 2024-04-05 01:29:48
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_fans")
@ApiModel(value = "claw_fans实体类",description = "用于存储传输claw_fans信息")
public class Fans {

    @MppMultiId
    @ApiModelProperty(name = "userId", value = "拥有粉丝的用户id", hidden = true)
    private Long userId;

    @MppMultiId
    @ApiModelProperty(name = "fansId", value = "粉丝的用户id", hidden = true)
    private Long fansId;

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
