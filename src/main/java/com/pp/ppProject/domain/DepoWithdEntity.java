package com.pp.ppProject.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.pp.ppProject.dto.request.DepoWithdDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //Entity임을 명시, Entity명 account
@Table(name = "DepoWithd")  //table명을 acount로 지정
@Getter  //getter 메소드 생성
@SequenceGenerator(  //시퀀스 생성기 설
		name = "TRANSACTION_SEQ_GENERATOR",
		sequenceName = "TRANSACTION_SEQ"
)
@Builder  //Builder 사용
@AllArgsConstructor //필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class DepoWithdEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQ_GENERATOR")
	private int tranNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberNo")
	private MemberEntity member;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "accountNo")
	private AccountEntity account;
	
	private int tranType;
	
	private int tranAmount;
	
	private int tranBalance;
	
	@DateTimeFormat(pattern = "yy/MM/dd")
	private LocalDate tranDate;
	private int tranCategory;
	private String tranDetail;
	
	public static DepoWithdEntity createTransactionEntity(DepoWithdDTO dto, int balance) {
		MemberEntity m = MemberEntity.builder().memberNo(dto.getMemberNo()).build();
		AccountEntity a = AccountEntity.builder().accountNo(dto.getAccountNo()).build();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		
		return DepoWithdEntity.builder()
				.member(m)
				.account(a)
				.tranType(dto.getTranType())
				.tranAmount(dto.getTranAmount())
				.tranBalance(balance)
				.tranDate(LocalDate.parse(dto.getTranDate(), formatter))
				.tranCategory(dto.getTranCategoryCode())
				.tranDetail(dto.getTranDetail())
				.build();
	}
	

}
