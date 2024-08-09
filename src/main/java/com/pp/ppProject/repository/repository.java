//repository
package com.pp.ppProject.repository;

import org.springframework.stereotype.Repository;

import com.pp.ppProject.domain.MemberEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class repository {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager(); 
	
	public MemberEntity save(MemberEntity m) {
		em.persist("abcd");
		return m;
	}
}