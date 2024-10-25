package com.pp.ppProject.dto.response;


import com.pp.ppProject.dto.ResultCode;

import lombok.Getter;

@Getter
public class Message {
	private ResultCode code;
	private String msg;
	private Object data;
	
	public static Message settingMsg(ResultCode code, String msg, Object data) {
		return new Message(code, msg, data);
	}
	
	public static Message settingMsg(ResultCode code, String msg) {
		return new Message(code, msg, null);
	}
	
	public Message (ResultCode code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
