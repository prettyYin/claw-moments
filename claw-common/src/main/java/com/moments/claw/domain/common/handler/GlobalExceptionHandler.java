package com.moments.claw.domain.common.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.exception.CustomException;
import com.moments.claw.domain.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理程序
 * <p>
 * 统一异常处理
 * 使用该注解表示开启了全局异常的捕获
 *
 * @author longjun
 * @version 1.0.0
 * @date 2023/04/04
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 处理自定义的业务异常
	 */
	@ExceptionHandler(value = CustomException.class)
	@ResponseBody
	public R customExceptionHandler(HttpServletRequest req, CustomException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生业务异常！原因是：{}", e.getMessage());
		
		return R.getExceptionResult(e.getCode(), e.getMessage());
	}

	/**
	 * 处理自定义的业务异常
	 */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public R bizExceptionHandler(HttpServletRequest req, BizException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生业务异常！原因是：{}", e.getErrorMsg());
		
		return R.getExceptionResult(e.getErrorCode(), e.getErrorMsg());
	}

	/**
	 * 处理空指针的异常
	 */
	@ExceptionHandler(value = NullPointerException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, NullPointerException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生空指针异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.NULL_POINT);
	}


	/**
	 * 处理索引越界异常
	 */
	@ExceptionHandler(value = IndexOutOfBoundsException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, IndexOutOfBoundsException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("索引越界异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.OUT_OF_INDEX_ERROR);
	}

	/**
	 * 处理类未找到异常
	 */
	@ExceptionHandler(value = ClassNotFoundException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, ClassNotFoundException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生类未找到异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.CLASS_NOT_FOUND);
	}


	/**
	 * 处理SQL异常
	 */
	@ExceptionHandler(value = SQLException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, SQLException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生SQL异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.SQL_EXCEPTION);
	}

	/**
	 * 处理IO异常
	 */
	@ExceptionHandler(value = IOException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, IOException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生IO异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.IO_EXCEPTION);
	}


	/**
	 * json转换异常处理程序
	 */
	@ExceptionHandler(value = JsonParseException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, JsonParseException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生JSON转换异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.JSON_PARSE_ERROR);
	}

	/**
	 * String转数字异常处理程序
	 */
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseBody
	public R exceptionsHandler(HttpServletRequest req, NumberFormatException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生String转数字异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.NUMBER_FORMAT_ERROR);
	}

	/**
	 * 前端参数不匹配异常处理程序
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public R exceptionsHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生前端参数不匹配异常！原因是:", e);
		
		return R.getExceptionResult(ResultEnum.PARAMETER_ERROR);
	}

	/**
	 * 参数校验异常
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class,MissingServletRequestParameterException.class})
	@ResponseBody
	public R handleValidException(HttpServletRequest req, Exception e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("发生前端参数校验失败异常！原因是:", e);
		return R.getExceptionResult(ResultEnum.PARAMETER_INVALID);
	}

	/**
	 * 处理其他异常
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, Exception e) {
		log.error("URL : " + req.getRequestURL().toString());
		log.error("HTTP_METHOD : " + req.getMethod());
		log.error("未知异常！原因是:", e);
		return R.getExceptionResult(ResultEnum.SYSTEM_EROOR);
	}


}