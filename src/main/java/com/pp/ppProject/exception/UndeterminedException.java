package com.pp.ppProject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UndeterminedException extends Exception{

	//직렬화 역직렬화시 클래스 버전의 호환 여부를 확인하기 위한 시리얼번호
	private static final long serialVersionUID = 7687813611318233743L;

	public UndeterminedException(String message) {
		super(message);
	}
}
