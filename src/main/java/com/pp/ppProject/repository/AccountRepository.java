package com.pp.ppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.ppProject.domain.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

}
