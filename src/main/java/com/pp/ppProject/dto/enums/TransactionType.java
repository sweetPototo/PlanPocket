package com.pp.ppProject.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
	
	WITHDRAW(0, "출금"),
	DEPOSIT(1,"입금");
	
	private final int transactionCode;
	private final String transactionName;
}
