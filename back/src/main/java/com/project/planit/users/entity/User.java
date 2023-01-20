package com.project.planit.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

/**
 * packageName    : com.project.planit.users.entity fileName       : User author         : SSAFY
 * date           : 2023-01-20 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-20        SSAFY       최초
 * 생성
 */
@Entity
@Getter
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id", nullable = false)
  private Long id;
  private String app_id;
  private String app_pwd;
  private String name;
  private Role role;
  private String email;

}
