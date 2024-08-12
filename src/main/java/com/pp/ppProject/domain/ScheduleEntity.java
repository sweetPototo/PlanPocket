//domain
package com.pp.ppProject.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

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
