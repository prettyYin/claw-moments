package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 领养/救助需求表(Require)表实体类
 *
 * @author chandler
 * @since 2024-03-11 22:03:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_require")
@ApiModel(value = "claw_require实体类",description = "用于存储传输claw_require信息")
public class Require extends BaseEntity {
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;

    @ApiModelProperty(name = "userId", value = "用户id")
    private Long userId;

    @ApiModelProperty(name = "memberId", value = "会员id")
    private Long memberId;

    @ApiModelProperty(name = "petId", value = "宠物id")
    private Long petId;
        
    @ApiModelProperty(name = "name", value = "需求名称")
    private String name;
        
    @ApiModelProperty(name = "status", value = "状态（0异常，1正常）")
    private Integer status;
}
