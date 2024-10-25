package com.pp.ppProject.dto.request;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRequestDTO {

	private String accountName;
	private String accountCategory;
	private String accountDetail;
	private String memberNo;
	private String accountBalance;
	
	 @ConstructorProperties({"accountName", "accountCategory", "accountDetail", "memberNo"})
	    public AccountRequestDTO(String accountName, String accountCategory, String accountDetail, String memberNo, String accountBalance) {
	        this.accountName = accountName;
	        this.accountCategory = accountCategory;
	        this.accountDetail = accountDetail;
	        this.memberNo = memberNo;
	        this.accountBalance = accountBalance;
	    }
	
	 public void memberNoSetting(String memberNo) {
		 this.memberNo = memberNo;
	 }
}
