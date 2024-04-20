package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 角色表(ClawRole)表实体类
 *
 * @author chandler
 * @since 2024-04-20 21:09:57
 */
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_role")
@ApiModel(value = "claw_role实体类",description = "用于存储传输claw_role信息")
public class Role extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "roleId", value = "主键", hidden = true)
    private Long roleId;
        
    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;
        
    @ApiModelProperty(name = "permissionKey", value = "权限标识符")
    private String permissionKey;
        
    @ApiModelProperty(name = "status", value = "角色状态（0禁用，1正常）")
    private Integer status;
}
