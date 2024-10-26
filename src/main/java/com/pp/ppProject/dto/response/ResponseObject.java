package com.pp.ppProject.dto.response;


import com.pp.ppProject.dto.enums.ResultCode;

import lombok.Getter;

@Getter
public class ResponseObject {
	private ResultCode code;
	private String message;
	private Object data;
	
	public static ResponseObject settingMsg(ResultCode code, String message, Object data) {
		return new ResponseObject(code, message, data);
	}
	
	public static ResponseObject settingMsg(ResultCode code, String message) {
		return new ResponseObject(code, message, null);
	}
	
	public ResponseObject (ResultCode code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
