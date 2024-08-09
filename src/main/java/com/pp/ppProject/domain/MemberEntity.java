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

}
