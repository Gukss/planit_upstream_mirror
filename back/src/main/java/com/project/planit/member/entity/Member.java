package com.project.planit.member.entity;

import javax.persistence.*;

import com.project.planit.util.BaseEntity;
import lombok.Getter;

/**
 * packageName    : com.project.planit.users.entity
 * fileName       : Member
 * author         : dongk
 * date           : 2023-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-20        dongk       최초 생성
 */
@Entity
@Getter
@Table(name="member")
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id", nullable = false) //회원번호
  private Long id;

  @Column(name ="member_app_id", nullable = false) //아이디
  private String appId;

  @Column(name ="member_app_pwd", nullable = false) //비밀번호
  private String appPwd;

  @Column(name ="member_name", nullable = false) //닉네임
  private String name;

  @Enumerated(EnumType.STRING) //이넘 타입은 문자열로
  private Role role; //역할

  private String email; //이메일

  @Embedded
  private BaseEntity baseEntity;
}
