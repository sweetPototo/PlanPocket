package com.pp.ppProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import com.pp.ppProject.domain.AccountEntity;
import com.pp.ppProject.domain.DepoWithdEntity;
import com.pp.ppProject.dto.ResultCode;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.DepoWithdDTO;
import com.pp.ppProject.dto.response.Message;
import com.pp.ppProject.repository.AccountRepository;
import com.pp.ppProject.repository.DepoWithdRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InformationServiceImpl implements InformationService {

	private final AccountRepository accountRepository;
	private final DepoWithdRepository depoRepository;
	
	@Override
	public Message addAccount(AccountRequestDTO dto) {
		AccountEntity account = AccountEntity.createAccountEntity(dto);
		log.info("added account name : " + account.getAccountName());
		try {
			accountRepository.save(account);
			return Message.settingMsg(ResultCode.SUCCESS_CREATE, "계좌 저장 성공");
		} catch(ConstraintViolationException e) {  //제약 조건 위반 시
			return Message.settingMsg(ResultCode.FAILED_BADREQUEST, "제약조건 위반으로 인한 저장 실패");
		}
	}

	@Override
	public List<AccountDTO> selectMemberNo(int memberNo) {
		List<AccountEntity> entity = accountRepository.findByMemberMemberNo(memberNo);
		List<AccountDTO> list = new ArrayList<>();
		for(AccountEntity e : entity) {
			AccountDTO dto = AccountDTO.builder()
					.accountNo(e.getAccountNo())
					.accountName(e.getAccountName())
					.accountBalance(e.getAccountBalance())
					.build();
			list.add(dto);
		}
		return list;
	}
	
	private AccountEntity changeEntityAccount(Optional<AccountEntity> accountOt) {
		AccountEntity account = AccountEntity.builder()
				.accountBalance(accountOt.get().getAccountBalance())
				.build();
		return account;
	}

	//DepoWithd에 거래내역이 입력되면 트리거가 발동 -> account에 자동으로 잔액 update
	@Override
	public Message addTran(DepoWithdDTO dto) {
		Optional<AccountEntity> accountOt = accountRepository.findById(dto.getAccountNo());
		AccountEntity account = changeEntityAccount(accountOt);
		int balance = 0;
		//0 = 지출, 1 = 입금
		if(dto.getTranType() == 1) {
			balance = account.getAccountBalance() + dto.getTranAmount();
		}else {
			balance = account.getAccountBalance() - dto.getTranAmount();
		}
		dto.setBalance(balance);
		DepoWithdEntity depo = DepoWithdEntity.createTransactionEntity(dto);
		try {
			depoRepository.save(depo);
		}catch(ConstraintViolationException e) {
			log.info("거래내역 저장 실패 : {}", getClass().getName());
		}
		return null;//이후 수정
	}

}
