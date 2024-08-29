package com.pp.ppProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputTransactionRequestDTO {

	private String accountNo;
	private String tranDate;
	private String tranType;
	private String tranCategoryCode;
	private String tranAmount;
	private String tranDetail;
}
