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
@Table(name = "member")
public class MemberEntity {

   @Id // 기본키 할당
   @GeneratedValue(strategy = GenerationType.IDENTITY)
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
