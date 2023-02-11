package com.project.planit.common.auth.controller;

import com.project.planit.common.auth.dto.ResponseAuth;
import com.project.planit.common.auth.jwt.JwtFilter;
import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.common.auth.service.AuthService;
import com.project.planit.common.auth.util.CookieUtils;
import com.project.planit.member.dto.SignInMemberRequest;
import com.project.planit.member.dto.SignInMemberResponse;
import com.project.planit.member.service.MemberService;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.common.auth.controller fileName       : authController author
 *         : SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class authController {
  private final AuthService authService;
  private final JwtProvider jwtProvider;

  @PostMapping("/sign-in") // Account 인증 API
  public ResponseEntity<SignInMemberResponse> authorize(@RequestBody SignInMemberRequest request, HttpServletResponse httpServletResponse){

    ResponseAuth responseAuth = authService.createAccessToken(request.getMemberAppId(),
        request.getMemberAppPwd());
    // response header 에도 넣고 응답 객체에도 넣는다.
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + responseAuth.getAccessToken());


    //tocken 2개 발급해서 하나는 쿠키, 하나는 db에 넣는다.
    CookieUtils.addCookie(httpServletResponse, "Bearer " +responseAuth.getAccessToken());


    // 응답
    SignInMemberResponse signInMemberResponse = authService.createSignInMemberResponse(
        responseAuth.getAccessToken());


    return ResponseEntity.ok().body(signInMemberResponse);
  }

  @PostMapping("/token/refresh") // 리프레시 토큰을 활용한 액세스 토큰 갱신
  public ResponseEntity<SignInMemberResponse> refreshToken(@RequestHeader String refreshToken, HttpServletResponse httpServletResponse) {

    ResponseAuth responseAuth = authService.refreshToken(refreshToken);

    // response header 에도 넣고 응답 객체에도 넣는다.
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + responseAuth.getAccessToken());

    //tocken 2개 발급해서 하나는 쿠키, 하나는 db에 넣는다.
    CookieUtils.addCookie(httpServletResponse, "Bearer " +responseAuth.getAccessToken());

    // 응답
    SignInMemberResponse signInMemberResponse = authService.createSignInMemberResponse(
        responseAuth.getAccessToken());

    return ResponseEntity.ok().body(signInMemberResponse);
  }
}
