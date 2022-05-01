package com.demo.exceptionhandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demo.response.BaseResponse;

@ControllerAdvice
public class DemoExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseResponse<Void> onConstraintValidationException(ConstraintViolationException e) {
		BaseResponse<Void> response = new BaseResponse<>();
		Map<String, String> errorMap = new HashMap<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
			response.setErrorMessages(errorMap);
		}
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseResponse<Void> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BaseResponse<Void> response = new BaseResponse<>();
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			response.setErrorMessages(errorMap);
		}
		return response;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseResponse<Void> onMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		BaseResponse<Void> response = new BaseResponse<>();
		response.setErrorMessages(Collections.singletonMap(ex.getParameterName(), "missing parameter"));
		return response;
	}
	
	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseResponse<Void> handleMissingParams(MissingPathVariableException ex) {
		BaseResponse<Void> response = new BaseResponse<>();
		response.setErrorMessages(Collections.singletonMap(ex.getParameter().getParameterName(), "missing parameter"));
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseResponse<Void> onException(Exception e) {
		BaseResponse<Void> response = new BaseResponse<>();
		response.setErrorMessages(Collections.singletonMap("generalError", e.getMessage()));
		return response;
	}
}
