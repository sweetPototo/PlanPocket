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
@Builder(toBuilder = true)
@Table(name = "member")
@SequenceGenerator(
    name = "MEMBER_SEQ_GENERATOR", 
    sequenceName = "member_seq", // 사용할 시퀀스 이름
    initialValue = 1, // 시퀀스 시작 값
    allocationSize = 1 // 증가 값
)

@AllArgsConstructor //필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성, 같은 패키지나 상송받은 클래스만 생성자 접근
public class MemberEntity {

   @Id // 기본키 할당
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
   private int memberNo;
   
   @Column(length = 50)
   private String memberId;
   
   @Column(length = 50)
   private String memberPasswd;
   
   @Column(length = 50)
   private String memberName;
   
   @Column(length = 11)
   private String memberTel;
   
   @Column(length = 50)
   private String memberEmail;
   
   @Column(length = 100)
   private String memberImage;
   
   @Column(length = 2000)
   private String memberIntro;
   
   @DateTimeFormat(pattern = "yy/MM/dd")
   private Date memberJoinDate;
   
   @DateTimeFormat(pattern = "yy/MM/dd")
   private Date memberOutDate;
   
}
