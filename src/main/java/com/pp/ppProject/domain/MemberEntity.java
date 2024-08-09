package com.pp.ppProject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MemberEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberNum;
	private String memberId;
	private String memberPw;
	private String memberName;
}
