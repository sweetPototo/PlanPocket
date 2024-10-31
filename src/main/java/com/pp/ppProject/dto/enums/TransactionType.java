package com.pp.ppProject.dto.enums;

import com.pp.ppProject.exception.UndeterminedException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
	
	WITHDRAW(0, "출금"),
	DEPOSIT(1,"입금");
	
	private final int transactionCode;
	private final String transactionName;
	
	public static TransactionType transperTransactionType(String dtoType) throws UndeterminedException {
		switch(dtoType) {
			case "0" : 
				return TransactionType.WITHDRAW;
			case "1" : 
				return TransactionType.DEPOSIT;
			default : 
				throw new UndeterminedException();
		}
	}
}
