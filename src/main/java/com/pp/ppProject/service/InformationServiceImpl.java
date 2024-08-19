package com.pp.ppProject.service;

import org.springframework.stereotype.Service;

import com.pp.ppProject.domain.AccountEntity;
import com.pp.ppProject.dto.request.AccountRequestDTO;
import com.pp.ppProject.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

	private final AccountRepository accountRepository;
	
	@Override
	public boolean addAccount(AccountRequestDTO dto) {
		AccountEntity account = AccountEntity.toEntity(dto);
		accountRepository.save(account);
		if(!accountRepository.findById(account.getAccountNo()).isEmpty())
			return true;
		else return false;
	}

}
