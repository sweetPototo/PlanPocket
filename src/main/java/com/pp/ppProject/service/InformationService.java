package com.pp.ppProject.service;

import java.util.List;

import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.DepoWithdDTO;
import com.pp.ppProject.dto.response.Message;

public interface InformationService {

	//계좌 등록하기
	public Message addAccount(AccountRequestDTO dto);
	
	//계좌 조회하기
	public List<AccountDTO> selectMemberNo(int memberNo);
	
	//거래 등록하기
	public Message addTran(DepoWithdDTO dto);
}
