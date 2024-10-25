package com.pp.ppProject.dto.request;

import java.beans.ConstructorProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InputTransactionRequestDTO {

	private String accountNo;
	private String tranDate;
	private String tranType;
	private String tranCategoryCode;
	private String tranAmount;
	private String tranDetail;
	private int memberNo;
	
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
//	@ConstructorProperties({"accountNo", "tranDate", "tranType", "tranCategoryCode", "tranAmount", "tranDetail"})
//	public InputTransactionRequestDTO(String accountNo, String tranDate, String tranType, String tranCategoryCode, String tranAmount, String tranDetail) {
//		this.accountNo = accountNo;
//		this.tranDate = tranDate;
//		this.tranType = tranType;
//		this.tranCategoryCode = tranCategoryCode;
//		this.tranAmount = tranAmount;
//		this.tranDetail = tranDetail;
//	}
}
