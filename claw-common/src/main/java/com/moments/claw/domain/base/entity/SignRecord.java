package com.moments.claw.domain.base.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moments.claw.domain.BaseEntity;

/**
 * 用户签到表(SignRecord)表实体类
 *
 * @author chandler
 * @since 2024-04-22 15:43:15
 */
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_sign_record")
@ApiModel(value = "claw_sign_record实体类",description = "用于存储传输claw_sign_record信息")
public class SignRecord extends BaseEntity {
    
    @MppMultiId
    @ApiModelProperty(name = "userId", value = "用户id", hidden = true)
    private Long userId;
    
    @MppMultiId
    @ApiModelProperty(name = "signinDate", value = "签到日期", hidden = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate signinDate;
}
