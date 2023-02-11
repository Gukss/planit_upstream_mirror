package com.project.planit.common.auth.service;

import com.project.planit.common.auth.dto.ResponseAuth;
import com.project.planit.member.dto.SignInMemberResponse;

/**
 * packageName    : com.project.planit.common.auth.service fileName       : AuthService author
 *   : SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */
public interface AuthService {
  public ResponseAuth createAccessToken(String memberAppId, String memberAppPwd);
  public ResponseAuth refreshToken(String refreshToken);
  public String reissueAccessToken(String oldAccessToken, String refreshToken);
  public SignInMemberResponse createSignInMemberResponse(String accessToken);
}
