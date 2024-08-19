package com.pp.ppProject.service;

import com.pp.ppProject.dto.request.AccountRequestDTO;

public interface InformationService {

	//계좌 등록하기
	public boolean addAccount(AccountRequestDTO dto);
}
