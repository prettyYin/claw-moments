package com.moments.claw.domain.common.exception;


import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.response.R;

/**
 * 业务异常
 * 自定义一个异常类，用于处理我们发生的业务异常
 *
 * @author claw-moments
 * @version 1.0.0
 * @date 2023/04/04
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	protected Integer errorCode;
	/**
	 * 错误信息
	 */
	protected String errorMsg;

	public BizException() {
		super();
	}

	public BizException(R errorInfoInterface) {
		super(errorInfoInterface.getCode().toString());
		this.errorCode = errorInfoInterface.getCode();
		this.errorMsg = errorInfoInterface.getMessage();
	}

	public BizException(R errorInfoInterface, Throwable cause) {
		super(errorInfoInterface.getCode().toString(), cause);
		this.errorCode = errorInfoInterface.getCode();
		this.errorMsg = errorInfoInterface.getMessage();
	}

	public BizException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public BizException(Integer errorCode, String errorMsg) {
		super(String.valueOf(errorCode));
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public BizException(ResultEnum resultEnum) {
		super(String.valueOf(resultEnum.getCode()));
		this.errorCode = resultEnum.getCode();
		this.errorMsg = resultEnum.getMsg();
	}

	public BizException(Integer errorCode, String errorMsg, Throwable cause) {
		super(String.valueOf(errorCode), cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}


	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}