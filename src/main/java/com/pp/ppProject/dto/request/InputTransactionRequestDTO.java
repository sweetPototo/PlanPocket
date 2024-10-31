package com.pp.ppProject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InputTransactionRequestDTO {

	@NotBlank(message = "계좌 정보를 입력해주세요.")
	private String accountNo;
	
	@NotNull(message = "거래일자를 입력해주세요.")
	private String tranDate;
	
	@NotBlank(message = "지출/입금을 선택해주세요.")
	private String tranType;
	
	@NotBlank(message = "거래 카테고리를 선택해주세요.")
	private String tranCategoryCode;
	
	@NotBlank(message = "금액을 입력해주세요.")
	private String tranAmount;
	
	@NotNull(message = "거래 내용을 입력해주세요.")
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
