package com.xxx.test;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Json implements Serializable {
	
	
	private String msg;
	
	private String code;
	
	private String result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
