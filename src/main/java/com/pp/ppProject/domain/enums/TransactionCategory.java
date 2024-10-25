package com.pp.ppProject.domain.enums;

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
}
