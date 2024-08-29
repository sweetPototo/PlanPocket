package com.pp.ppProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pp.ppProject.domain.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{
	
	//로그인 계정에 해당하는 계좌 찾기
	public List<AccountEntity> findByMemberMemberNo(int memberNo);
	
}
