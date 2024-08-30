package com.pp.ppProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pp.ppProject.domain.AccountEntity;
import com.pp.ppProject.domain.DepoWithdEntity;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.dto.request.DepoWithdDTO;
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
	public boolean addAccount(AccountRequestDTO dto) {
		AccountEntity account = AccountEntity.createAccountEntity(dto);
		log.info("added account name : " + account.getAccountName());
		accountRepository.save(account);
		if(!accountRepository.findById(account.getAccountNo()).isEmpty())
			return true;
		else return false;
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

	//DepoWithd에 거래내역이 입력되면 트리거가 발동 -> account에 자동으로 잔액 update
	@Override
	public boolean addTran(DepoWithdDTO dto) {
		Optional<AccountEntity> account = accountRepository.findById(dto.getAccountNo());
		int balance = 0;
		//0 = 지출, 1 = 입금
		if(dto.getTranType() == 1)
			balance = account.get().getAccountBalance() + dto.getTranAmount();
		else
			balance = account.get().getAccountBalance() - dto.getTranAmount();
		DepoWithdEntity depo = DepoWithdEntity.createTransactionEntity(dto, balance);
		depoRepository.save(depo);
		
		DepoWithdEntity e = depoRepository.findTopByMemberMemberNoOrderByTranDateDesc(dto.getMemberNo());
		int b = e.getTranBalance();
		log.info("The Balance of Top Date = " + b);
		
		if(!depoRepository.findById(depo.getTranNo()).isEmpty())
			return true;
		else return false;
	}

}
