package com.pp.ppProject.dto.request;

import com.pp.ppProject.dto.enums.TransactionCategory;
import com.pp.ppProject.dto.enums.TransactionType;
import com.pp.ppProject.exception.UndeterminedException;

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
	private TransactionCategory tranCategory;
	private int tranAmount;
	private int tranBalance;
	private String tranDetail;
	private int memberNo;
	
	public void setBalance(int balance) {
		this.tranBalance = balance;
	}
	
	public static DepoWithdDTO of(InputTransactionRequestDTO inputDto) throws UndeterminedException {
		return DepoWithdDTO.builder()
				.accountNo(Integer.parseInt(inputDto.getAccountNo()))
				.tranDate(inputDto.getTranDate())
				.tranType(TransactionType.transperTransactionType(inputDto.getTranType()))
				.tranCategory(TransactionCategory.transperTransactionCategory(inputDto.getTranCategoryCode()))
				.tranAmount(Integer.parseInt(inputDto.getTranAmount()))
				.tranDetail(inputDto.getTranDetail())
				.memberNo(inputDto.getMemberNo())
				.build();
	}
}
