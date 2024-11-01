package com.pp.ppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.ppProject.domain.DepoWithdEntity;

public interface DepoWithdRepository extends JpaRepository<DepoWithdEntity, Integer> {

	public DepoWithdEntity findTopByMemberMemberNoOrderByTranDateDesc(int memberNo);
}
