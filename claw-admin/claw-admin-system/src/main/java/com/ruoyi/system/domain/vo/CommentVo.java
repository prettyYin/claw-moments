package com.ruoyi.system.domain.vo;

import com.moments.claw.domain.base.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends Comment {

    // 文章标题
    private String title;
}
