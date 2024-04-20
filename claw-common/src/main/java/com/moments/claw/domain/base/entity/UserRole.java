package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 用户、角色关联表(ClawUserRole)表实体类
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
@TableName("claw_user_role")
@ApiModel(value = "claw_user_role实体类",description = "用于存储传输claw_user_role信息")
public class UserRole extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "userId", value = "用户主键", hidden = true)
    private Long userId;
    
    @TableId
    @ApiModelProperty(name = "roleId", value = "角色主键", hidden = true)  
    private Long roleId;
        
}
