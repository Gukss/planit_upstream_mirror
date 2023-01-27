package com.project.planit.member.dto;

import com.project.planit.member.entity.Role;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class createMemberRequest {
  @NotBlank
  private String memberAppId;
  @NotBlank
  private String memberAppPwd;
  @NotBlank
  private String memberName;
  @NotBlank
  private String memberEmail;
  @Enumerated(EnumType.STRING)
  private Role role;
}
