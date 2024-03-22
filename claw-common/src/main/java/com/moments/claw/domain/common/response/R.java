package com.moments.claw.domain.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moments.claw.domain.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//这个注解表示变量为空的时候构造json就不带上这个变量
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {
	/**
	 * 结果状态码
	 */
	private Integer code;
	/**
	 * 响应结果描述
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private T data;

	public R(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public R(T data) {
		this.data = data;
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = "操作成功";
	}

	public static <T> R<T> success() {
		return build(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
	}

	public static <T> R<T> success(T data) {
		return build(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
	}

	public static <T> R<T> success(Integer code, T data) {
		return build(code, ResultEnum.SUCCESS.getMsg(), data);
	}

	public static <T> R<T> success(Integer code, String msg) {
		return build(code, msg);
	}

	public static <T> R<T> success(Integer code, T data, String msg) {
		return build(code, msg, data);
	}

	public static <T> R<T> fail() {
		return build(ResultEnum.SYSTEM_EROOR.getCode(), ResultEnum.SYSTEM_EROOR.getMsg());
	}

	public static <T> R<T> fail(T data) {
		return build(ResultEnum.SYSTEM_EROOR.getCode(), ResultEnum.SYSTEM_EROOR.getMsg(), data);
	}

	public static <T> R<T> fail(Integer code, T data) {
		return build(code, ResultEnum.SYSTEM_EROOR.getMsg(), data);
	}

	public static <T> R<T> fail(Integer code, String msg) {
		return build(code, msg);
	}

	public static <T> R<T> fail(Integer code, T data, String msg) {
		return build(code, msg, data);
	}

	public static <T> R<T> build(Integer code, String message) {
		return new R(code, message);
	}

	/**
	 * 静态方法，返回前端实体结果
	 *
	 * @param code    状态码
	 * @param message 消息
	 * @param data    数据
	 * @return 前端实体结果
	 */
	public static <T> R<T> build(Integer code, String message, T data) {
		return new R(code, message, data);
	}

	/**
	 * 返回成功的结果实体
	 *
	 * @param message 消息
	 * @param data    数据
	 * @return 实体
	 */
	public static <T> R<T> getSuccessResult(String message, Object data) {
		R result = new R();
		result.code = ResultEnum.SUCCESS.getCode();
		result.message = message;
		result.data = data;
		return result;
	}

	/**
	 * 返回无需data的成功结果实体
	 *
	 * @param message 消息内容
	 * @return 返回结果
	 */
	public static <T> R<T> getSuccessResultOnlyMessage(String message) {
		R result = new R();
		result.code = ResultEnum.SUCCESS.getCode();
		result.message = message;
		result.data = null;
		return result;
	}

	/**
	 * 获取一个异常结果
	 *
	 * @param code    错误码
	 * @param message 自定义异常信息
	 * @return FrontResult
	 */
	public static <T> R<T> getExceptionResult(Integer code, String message) {
		R result = new R();
		result.code = (code == null) ? ResultEnum.CODE_EXCEPTION.getCode() : code;
		result.message = message.isEmpty() ? ResultEnum.CODE_EXCEPTION.getMsg() : message;
		return result;
	}

	/**
	 * 得到异常结果
	 *
	 * @param resultEnum 枚举结果代码
	 * @return {@link R}
	 */
	public static <T> R<T> getExceptionResult(ResultEnum resultEnum) {
		R result = new R();
		Integer code = resultEnum.getCode();
		String msg = resultEnum.getMsg();
		result.code = (code == null) ? ResultEnum.CODE_EXCEPTION.getCode() : code;
		result.message = msg.isEmpty() ? ResultEnum.CODE_EXCEPTION.getMsg() : msg;
		return result;
	}
}