package com.pp.ppProject.dto.request;

import java.beans.ConstructorProperties;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRequestDTO {

	private String accountName;
	private String accountCategory;
	private String accountDetail;
	private String memberNo;
	
	 @ConstructorProperties({"accountName", "accountCategory", "accountDetail", "memberNo"})
	    public AccountRequestDTO(String accountName, String accountCategory, String accountDetail, String memberNo) {
	        this.accountName = accountName;
	        this.accountCategory = accountCategory;
	        this.accountDetail = accountDetail;
	        this.memberNo = memberNo;
	    }
	
}
