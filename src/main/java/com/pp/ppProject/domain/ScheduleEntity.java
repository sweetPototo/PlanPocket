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

@Entity
@Getter
@Builder
@SequenceGenerator(
	    name = "SCHEDULE_SEQ_GENERATOR", 
	    sequenceName = "schedule_seq",
	    initialValue = 1,
	    allocationSize = 1 
	)
@Table(name = "schedule")
@AllArgsConstructor //필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class ScheduleEntity {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ_GENERATOR" )
	private int scheNo;
	
	@Column(length = 50)
	private String memberId;
	
	@Column(length = 2000)
	private String scheContent;
	
	@DateTimeFormat(pattern = "yy/MM/dd")
	private Date scheWriteDate;
	
	@Column(length = 2000)
	private int scheGood;
		
}
