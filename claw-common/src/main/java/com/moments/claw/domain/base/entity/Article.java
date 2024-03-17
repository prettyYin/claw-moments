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
 * (Article)表实体类
 *
 * @author chandler
 * @since 2024-03-17 19:44:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_article")
@ApiModel(value = "claw_article实体类",description = "用于存储传输claw_article信息")
public class Article extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "title", value = "标题")
    private String title;
        
    @ApiModelProperty(name = "content", value = "文章内容")
    private String content;
        
    @ApiModelProperty(name = "summary", value = "文章摘要")
    private String summary;
        
    @ApiModelProperty(name = "categoryId", value = "分类id")
    private Integer categoryId;
        
    @ApiModelProperty(name = "thumbnailId", value = "缩略图id")
    private String thumbnailId;
        
    @ApiModelProperty(name = "isOp", value = "是否置顶（0不是，1是）")
    private Integer isOp;
        
    @ApiModelProperty(name = "viewCount", value = "访问量")
    private Long viewCount;
        
    @ApiModelProperty(name = "isComment", value = "是否允许评论（0否，1是）")
    private Integer isComment;
        
    @ApiModelProperty(name = "status", value = "状态（0异常，1发布，2草稿，3禁用）")
    private Integer status;
        
}
