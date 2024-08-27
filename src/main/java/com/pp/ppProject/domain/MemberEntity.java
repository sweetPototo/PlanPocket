package com.pp.ppProject.domain;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "member")
@SequenceGenerator(
    name = "MEMBER_SEQ_GENERATOR", 
    sequenceName = "member_seq", // 사용할 시퀀스 이름
    initialValue = 1, // 시퀀스 시작 값
    allocationSize = 1 // 증가 값
)
@AllArgsConstructor // 필드의 모든 값을 매개변수로 받는 생성자 생성
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberEntity {

   @Id // 기본키 할당
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
   private int memberNo;

   @Column(length = 50, nullable = false) // NOT NULL 설정
   private String memberId;

   @Column(length = 50, nullable = false) // NOT NULL 설정
   private String memberPasswd;

   @Column(length = 50, nullable = false) // NOT NULL 설정
   private String memberName;

   @Column(length = 11, nullable = false) // NOT NULL 설정
   private String memberTel;

   @Column(length = 50, nullable = false) // NOT NULL 설정
   private String memberEmail;

   @Column(length = 100)
   private String memberImage;

   @Column(length = 2000)
   private String memberIntro;

   private LocalDate memberJoinDate;

   private LocalDate memberOutDate;

   @PrePersist
   protected void onCreate() {
       if (memberJoinDate == null) {
           memberJoinDate = LocalDate.now();
       }
   }
}
