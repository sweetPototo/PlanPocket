package com.pp.ppProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pp.ppProject.domain.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{
	
	@Query(value = "select * from account where memberNo = :memberNo", nativeQuery = true)
	public List<AccountEntity> searchAccount(@Param("memberNo")int memberNo);

}
