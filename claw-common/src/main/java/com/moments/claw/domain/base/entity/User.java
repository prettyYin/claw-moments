package com.moments.claw.domain.base.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * (ClawUser)表实体类
 *
 * @author chandler
 * @since 2024-03-18 21:28:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_user")
@ApiModel(value = "claw_user实体类",description = "用于存储传输claw_user信息")
public class User extends BaseEntity {
    
    @TableId
    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    private Long id;
        
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;
        
    @ApiModelProperty(name = "passwordHash", value = "密码")
    private String passwordHash;

    @ApiModelProperty(name = "group", value = "登陆方式")
    private String group;

    @ApiModelProperty(name = "merchantId", value = "商人id")
    private Long merchantId;
        
    @ApiModelProperty(name = "storeId", value = "店铺id")
    private Long storeId;
        
    @ApiModelProperty(name = "authKey", value = "$column.comment")
    private String authKey;
        
    @ApiModelProperty(name = "passwordResetToken", value = "密码重置令牌")
    private String passwordResetToken;
        
    @ApiModelProperty(name = "mobileResetToken", value = "手机号重置令牌")
    private String mobileResetToken;
        
    @ApiModelProperty(name = "realname", value = "真实姓名")
    private String realname;
        
    @ApiModelProperty(name = "nickname", value = "网名")
    private String nickname;
        
    @ApiModelProperty(name = "headPortrait", value = "头像")
    private String headPortrait;
        
    @ApiModelProperty(name = "gender", value = "性别（0：保密；1：男；2：女）")
    private Integer gender;
        
    @ApiModelProperty(name = "weixin", value = "微信号")
    private String weixin;
        
    @ApiModelProperty(name = "qq", value = "qq号")
    private String qq;
        
    @ApiModelProperty(name = "email", value = "email")
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "birthday", value = "出生年月")
    private Date birthday;
        
    @ApiModelProperty(name = "provinceId", value = "省行政编码")
    private String provinceId;
        
    @ApiModelProperty(name = "cityId", value = "市行政编码")
    private String cityId;
        
    @ApiModelProperty(name = "areaId", value = "地区行政编码")
    private String areaId;
        
    @ApiModelProperty(name = "address", value = "详细住址")
    private String address;
        
    @ApiModelProperty(name = "mobile", value = "手机号")
    private String mobile;
        
    @ApiModelProperty(name = "type", value = "用户类型")
    private Integer type;
        
    @ApiModelProperty(name = "telNo", value = "电话号码")
    private String telNo;
        
    @ApiModelProperty(name = "bgImage", value = "背景图片")
    private String bgImage;
        
    @ApiModelProperty(name = "visitCount", value = "被访次数")
    private Long visitCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    
    @ApiModelProperty(name = "lastLoginTime", value = "上次登录时间")
    private Date lastLoginTime;
        
    @ApiModelProperty(name = "lastLoginIp", value = "上次登录ip")
    private String lastLoginIp;
        
    @ApiModelProperty(name = "currentLevel", value = "当前会员等级")
    private Integer currentLevel;
        
    @ApiModelProperty(name = "follow", value = "关注数")
    private Long follow;
        
    @ApiModelProperty(name = "fans", value = "粉丝数")
    private Long fans;
        
    @ApiModelProperty(name = "promoterCode", value = "推广码（如果有推广功能）")
    private String promoterCode;
        
}
