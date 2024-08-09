//repository
package com.pp.ppProject.repository;

import org.springframework.stereotype.Repository;
import com.pp.ppProject.domain.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
	
    public MemberEntity save(MemberEntity m) {
        em.persist(m); // 'm' 객체를 영속성 컨텍스트에 저장
        return m; // 저장된 엔티티를 반환
    }
}
