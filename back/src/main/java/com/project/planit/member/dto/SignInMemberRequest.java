package com.project.planit.member.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.member.dto fileName       : SignInMemberRequest author
 *  : SSAFY date           : 2023-02-08 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-08        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SignInMemberRequest {
  @NotNull
  private String memberAppId;

  @NotNull
  private String memberAppPwd;
}
