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
 * (ClawFiles)表实体类
 *
 * @author chandler
 * @since 2024-03-17 17:29:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_files")
@ApiModel(value = "claw_files实体类", description = "用于存储传输claw_files信息")
public class Files extends BaseEntity {

	@TableId
	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "fileId", value = "文件id")
	private String fileId;

	@ApiModelProperty(name = "fileUrl", value = "文件路径")
	private String fileUrl;

	@ApiModelProperty(name = "fileName", value = "文件名")
	private String fileName;

	@ApiModelProperty(name = "fileSize", value = "文件大小（单位为byte）")
	private Integer fileSize;

	@ApiModelProperty(name = "fileType", value = "文件类型")
	private String fileType;

	@ApiModelProperty(name = "status", value = "状态（0异常，1正常）")
	private Integer status;

	@ApiModelProperty(name = "remark", value = "备注")
	private String remark;
}
