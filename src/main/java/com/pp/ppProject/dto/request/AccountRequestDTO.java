package com.pp.ppProject.dto.request;

import java.beans.ConstructorProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter  //@RequestBody때문에 붙임g
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRequestDTO {

	@NotBlank(message = "계좌명을 입력해주세요.")
	private String accountName;
	
	@NotBlank(message = "계좌 카테고리를 선택해주세요.")
	private String accountCategory;
	
	@NotNull(message = "계좌 설명을 입력해주세요.")
	private String accountDetail;
	
	private String memberNo;
	
	@NotBlank(message = "계좌 잔액을 입력해주세요.")
	private String accountBalance;
	
	public void memberNoSetting(String memberNo) {
		this.memberNo = memberNo;
	 }
}
