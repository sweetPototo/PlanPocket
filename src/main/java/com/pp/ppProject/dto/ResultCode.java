package com.pp.ppProject.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {

	SUCCESS_CLEAR(HttpStatus.OK),  //요청 성공
	SUCCESS_CREATE(HttpStatus.CREATED),  //등록 성공
	FAILED_BADREQUEST(HttpStatus.BAD_REQUEST);  //잘못된 요청
	
	private final HttpStatus status;
	
}
