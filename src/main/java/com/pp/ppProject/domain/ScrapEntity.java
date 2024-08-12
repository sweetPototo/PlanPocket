package com.pp.ppProject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name ="scrap") // table명을 scrap 로 지정
@Entity // Entity임을 명시, Entity명 scrap
@Getter // getter 메소드 생성
@Builder // Builder 사용
@SequenceGenerator(  //시퀀스 생성기 설
		name = "Scrap_SEQ_GENERATOR",
		sequenceName = "Scrap_SEQ",
	    initialValue = 1,
	    allocationSize = 1 
)
@AllArgsConstructor //필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class ScrapEntity {

	@Id // 기본키 할당
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SCRAP_SEQ_GENERATOR" )
	private int scrapNo;
	
	private int scheNo;
	
	private int memberNo;
	
}
