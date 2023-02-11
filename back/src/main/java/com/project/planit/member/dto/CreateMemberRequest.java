package com.project.planit.member.dto;

import com.project.planit.member.entity.Role;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.project.planit.util.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.member.dto fileName       : MemberInfo author         : SSAFY
 * date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreateMemberRequest {
  @NotNull
  private String memberAppId;

  @NotNull
  private String memberAppPwd;

  @NotNull
  private String memberName;

  @NotNull
  private String memberEmail;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Role role;

}
