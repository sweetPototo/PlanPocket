<<<<<<< HEAD
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
=======
//domain
package com.pp.ppProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MemberEntity {

	@Id // 기본키 할당
	@GeneratedValue
	private int id;

	private String name;

	
	
	private String homeAddress;

	
	
	private String workAddress;

>>>>>>> 7fa0a2c664df86351c30dee3c1c6ce634ba33d5d
}
