package com.moments.claw.domain.common.constant;

public class GlobalConstants {
	/**
	 * 删除标记
	 */
	public static final Integer DEL = 0;
	/**
	 * 未删除标记
	 */
	public static final Integer UN_DEL = -1;
	/**
	 * 异常状态
	 */
	public static final Integer UN_NORMAL_STATUS = -1;
	/**
	 * 正常状态
	 */
	public static final Integer NORMAL_STATUS = 1;
	/**
	 * 发布状态
	 */
	public static final Integer PUBLISH_STATUS = 1;
	/**
	 * 草稿状态
	 */
	public static final Integer UNPUBLISH_STATUS = 2;
	/**
	 * 禁用状态
	 */
	public static final Integer FORBIDDEN_STATUS = 3;
	/**
	 * parent_id=0
	 */
	public static final Long ROOT_PARENT_ID = -1L;
	/**
	 * 报名类型（1已报名;2审核中;3未报名）
	 */
	public static final Integer ENROLL_TYPE = 1;
	/**
	 * 报名类型（1已报名;2审核中;3未报名）
	 */
	public static final Integer AUDIT_TYPE = 2;
	/**
	 * 报名类型（1已报名;2审核中;3未报名）
	 */
	public static final Integer UN_ENROLL_TYPE = 3;
	/**
	 * 点赞类型（1已点赞，2未点赞）
	 */
	public static final Integer THUMB_UP_TYPE = 1;
	/**
	 * 点赞类型（1已点赞，2未点赞）
	 */
	public static final Integer UN_THUMB_UP_TYPE = 2;
	/**
	 * 文件id最大长度
	 */
	public static final Integer FILE_ID_MAX_LENGTH = 16;

	/**
	 * 未读
	 */
	public static final Integer UN_READ = 0;

	/**
	 * 已读
	 */
	public static final Integer READED = 0;
}
