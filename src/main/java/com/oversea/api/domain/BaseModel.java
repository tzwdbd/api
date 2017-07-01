package com.oversea.api.domain;

import java.io.Serializable;

import com.oversea.api.enums.ApiCode;

public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = -6874769822871553042L;
	
	private String code;
	private String message;
	
	public BaseModel() {}
	
	public BaseModel(ApiCode apiCode) {
		this(apiCode.getCode(), apiCode.getMessage());
	}
	
	public BaseModel(String message) {
		this(ApiCode.CODE_FAIL.getCode(), message);
	}
	
	public BaseModel(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
