//repository
package com.pp.ppProject.repository;

import org.springframework.stereotype.Repository;

import com.pp.ppProject.domain.MemberEntity;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class repository {

	private final EntityManager em;
	
	public MemberEntity save(MemberEntity m) {
		em.persist(m);
		return m;
	}
}