package com.pp.ppProject.service;

import java.util.List;

import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;

public interface InformationService {

	//계좌 등록하기
	public boolean addAccount(AccountRequestDTO dto);
	
	//계좌 조회하기
	public List<AccountDTO> selectMemberNo(int memberNo);
}
