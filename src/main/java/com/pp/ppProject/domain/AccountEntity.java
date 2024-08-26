package com.pp.ppProject.domain;

import com.pp.ppProject.dto.request.AccountRequestDTO;

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
@Table(name = "account")  //table명을 acount로 지정
@Getter  //getter 메소드 생성
@SequenceGenerator(  //시퀀스 생성기 설정
		name = "ACCOUNT_SEQ_GENERATOR",
		sequenceName = "ACCOUNT_SEQ"
)
@Builder  //Builder 사용
@AllArgsConstructor //필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ_GENERATOR")
	private int accountNo;
	private String accountName;
	private int accountCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberNo")
	private MemberEntity member;
	private String accountDetail;
	
//	public static AccountEntity createAccountEntity(AccountRequestDTO dto) {
//		return AccountEntity.builder()
//				.accountName(dto.getAccountName())
//				.accountCategory(Integer.parseInt(dto.getAccountCategory()))
//				.accountDetail(dto.getAccountDetail())
//				.memberNo(Integer.parseInt(dto.getMemberNo()))
//				.build();
//	}
	
	public static AccountEntity createAccountEntity(AccountRequestDTO dto) {
		MemberEntity m = MemberEntity.builder()
				.memberNo(Integer.parseInt(dto.getMemberNo()))
				.build();
		return AccountEntity.builder()
				.accountName(dto.getAccountName())
				.accountCategory(Integer.parseInt(dto.getAccountCategory()))
				.accountDetail(dto.getAccountDetail())
				.member(m)
				.build();
	}
}
