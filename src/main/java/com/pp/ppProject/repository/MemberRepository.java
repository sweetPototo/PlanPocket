package com.pp.ppProject.repository;

import com.pp.ppProject.domain.MemberEntity;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    MemberEntity findById (int id);
}
