package com.xxx.utils;

import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean success = true;

	private String msg = "";

	private Object result = null;
	
	private String code;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setFailure(){
		this.success = false;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
