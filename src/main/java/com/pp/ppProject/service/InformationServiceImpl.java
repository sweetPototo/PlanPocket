package com.pp.ppProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pp.ppProject.domain.AccountEntity;
import com.pp.ppProject.dto.request.AccountDTO;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InformationServiceImpl implements InformationService {

	private final AccountRepository accountRepository;
	
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
		List<AccountEntity> entity = accountRepository.searchAccount(memberNo);
		List<AccountDTO> list = new ArrayList<>();
		for(AccountEntity e : entity) {
			AccountDTO dto = AccountDTO.builder()
					.accountNo(e.getAccountNo())
					.accountName(e.getAccountName())
					.build();
			list.add(dto);
		}
		return list;
	}

}
