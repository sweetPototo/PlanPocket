package com.pp.ppProject.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountCategory {
	ACCOUNT(1, "계좌"),
	CASH(2, "현금"),
	POINT(3, "포인트"),
	CREADIT(4, "신용카드");
	
	private final int accountCategoryCode;
	private final String accountCategoryName;

}
