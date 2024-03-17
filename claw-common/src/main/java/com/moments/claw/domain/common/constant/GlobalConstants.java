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
	public static final Long ROOT_PARENT_ID = 0L;
}
