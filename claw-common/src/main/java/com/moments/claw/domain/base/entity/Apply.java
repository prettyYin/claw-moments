package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 领养申请表(ClawApply)表实体类
 *
 * @author chandler
 * @since 2024-03-30 16:08:29
 */
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_apply")
@ApiModel(value = "claw_apply实体类",description = "用于存储传输claw_apply信息")
public class Apply extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "userId", value = "用户id")
    private Long userId;
        
    @ApiModelProperty(name = "articleId", value = "文章id")
    private Long articleId;
        
    @ApiModelProperty(name = "career", value = "职业")
    private String career;
        
    @ApiModelProperty(name = "content", value = "申请语")
    private String content;
        
    @ApiModelProperty(name = "experience", value = "养宠经验")
    private String experience;
        
    @ApiModelProperty(name = "housing", value = "住房情况（0自有住房，1整租，2合租）")
    private Integer housing;
        
    @ApiModelProperty(name = "maritals", value = "婚姻状况（0单身，1已婚，2热恋）")
    private Integer maritals;
        
}
