package com.pp.ppProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DepoWithdDTO {

	private int accountNo;
	private String tranDate;
	private int tranType;
	private int tranCategoryCode;
	private int tranAmount;
	private int tranBalance;
	private String tranDetail;
	private int memberNo;
	
	public void setBalance(int balance) {
		this.tranBalance = balance;
	}
	
	public static DepoWithdDTO createTranDTO(InputTransactionRequestDTO inputDto) {
		return DepoWithdDTO.builder()
				.accountNo(Integer.parseInt(inputDto.getAccountNo()))
				.tranDate(inputDto.getTranDate())
				.tranType(Integer.parseInt(inputDto.getTranType()))
				.tranCategoryCode(Integer.parseInt(inputDto.getTranCategoryCode()))
				.tranAmount(Integer.parseInt(inputDto.getTranAmount()))
				.tranDetail(inputDto.getTranDetail())
				.memberNo(inputDto.getMemberNo())
				.build();
	}
}
