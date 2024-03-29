package com.moments.claw.domain.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * (ClawActivityArticle)表实体类
 *
 * @author chandler
 * @since 2024-03-24 03:39:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_activity_article")
@ApiModel(value = "claw_activity_article实体类",description = "用于存储传输claw_activity_article信息")
public class ActivityArticle extends BaseEntity {
    
    @ApiModelProperty(name = "activityId", value = "活动id（活动表id）", hidden = true)
    private Long activityId;
        
    @ApiModelProperty(name = "articleId", value = "文章id（文章表id）")
    private Long articleId;
        
}
