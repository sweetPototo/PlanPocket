//domain
package com.pp.ppProject.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // Entity임을 명시, Entity명 schedule
@Getter // getter 메소드 생성
@Builder // Builder 사용
@SequenceGenerator( // sequence 생성
	    name = "SCHEDULE_SEQ_GENERATOR", // 사용할 sequence 이름
	    sequenceName = "schedule_seq",
	    initialValue = 1,
	    allocationSize = 1 
	)
@Table(name = "schedule") // table명을 schedule 로 지정
@AllArgsConstructor // 필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class ScheduleEntity {
	
	@Id // 기본키 할당
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ_GENERATOR" )
	private int scheNo;
	
	private int memberNo ;
	
	@Column(length = 2000)
	private String scheContent;
	
	@DateTimeFormat(pattern = "yy/MM/dd")
	private Date scheWriteDate;
	
	@Column(length = 2000)
	private int scheGood;
		
}
