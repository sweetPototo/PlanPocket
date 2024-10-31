package com.pp.ppProject.dto.request;

import com.pp.ppProject.dto.enums.TransactionCategory;
import com.pp.ppProject.dto.enums.TransactionType;

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
	private TransactionType tranType;
	private TransactionCategory tranCategoryCode;
	private int tranAmount;
	private int tranBalance;
	private String tranDetail;
	private int memberNo;
	
	public void setBalance(int balance) {
		this.tranBalance = balance;
	}
	
	private static TransactionType transperTransactionType(String dtoType) throws Exception {
		switch(dtoType) {
			case "0" : 
				return TransactionType.WITHDRAW;
			case "1" : 
				return TransactionType.DEPOSIT;
			default : 
				throw new Exception();  //Exception 수정 예정
		}
	}
	
	public static DepoWithdDTO createTranDTO(InputTransactionRequestDTO inputDto) {
		return DepoWithdDTO.builder()
				.accountNo(Integer.parseInt(inputDto.getAccountNo()))
				.tranDate(inputDto.getTranDate())
				.tranType(transperTransactionType(inputDto.getTranType()))
				.tranCategoryCode(Integer.parseInt(inputDto.getTranCategoryCode()))
				.tranAmount(Integer.parseInt(inputDto.getTranAmount()))
				.tranDetail(inputDto.getTranDetail())
				.memberNo(inputDto.getMemberNo())
				.build();
	}
}
