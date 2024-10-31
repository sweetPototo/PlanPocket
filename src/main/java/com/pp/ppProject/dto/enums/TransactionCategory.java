package com.pp.ppProject.dto.enums;

import com.pp.ppProject.exception.UndeterminedException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionCategory {
	UTILITYES(1, "공과금"),
	FOOD(2, "식비"),
	HOBBY(3, "취미"),
	DAILY(4, "생필품"),
	MEDICAL(5, "의료비"),
	TRANSPORT(6, "교통비"),
	ETC(7, "기타");
	
	private final int tranCateCode;
	private final String tranCateName;
	
	public static TransactionCategory transperTransactionCategory(String dtoType) throws UndeterminedException {
		switch(dtoType) {
			case "1" : 
				return TransactionCategory.UTILITYES;
			case "2" : 
				return TransactionCategory.FOOD;
			case "3" :
				return TransactionCategory.HOBBY;
			case "4" :
				return TransactionCategory.DAILY;
			case "5" :
				return TransactionCategory.MEDICAL;
			case "6" :
				return TransactionCategory.TRANSPORT;
			case "7" :
				return TransactionCategory.ETC;
			default : 
				throw new UndeterminedException();
		}
	}
	
}
