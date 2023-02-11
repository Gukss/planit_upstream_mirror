package com.project.planit.common.auth.jwt;

import com.project.planit.common.auth.userDetails.PrincipalDetails;
import com.project.planit.member.entity.Member;
import com.project.planit.member.entity.Role;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
/**
 * packageName    : com.project.planit.common.auth.jwt fileName       : JwtAccessSuccessHandler
 * author         : SSAFY date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        SSAFY       최초
 * 생성
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAccessSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final JwtRefreshProvider jwtRefreshProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    Member member = principalDetails.getMember();

    String accessToken = jwtProvider.createAccessToken(authentication, member.getId(), member.getName());
    String refreshToken = jwtRefreshProvider.createRefreshToken(authentication);

    save(refreshToken, principalDetails);

    ResponseCookie cookie = ResponseCookie.from("access", accessToken)
        .httpOnly(true)
        .maxAge(JwtProvider.REFRESH_TOKEN_VALIDATE_TIME)
        .path("/")
        .build();

    clearAuthenticationAttributes(request, response);

    response.addHeader("Set-Cookie", cookie.toString());
    response.getWriter().write(accessToken);
  }

//  private void saveOrUpdateUser(String refreshToken, PrincipalDetails principalDetails) {
//    Optional<Member> opt = memberRepository.findByAppId(principalDetails.getUsername());
//    Member member
//
//    if (opt.isEmpty()) {
//      member = Member.builder()
//          .email(principalDetails.)
//          .nickname(principalDetails.getNickname())
//          .refreshToken(refreshToken)
//          .build();
//    } else {
//      user = opt.get();
//      user.updateRefreshToken(refreshToken);
//    }
//
//    userRepository.save(user);
//  }

  private void save(String refreshToken, PrincipalDetails principalDetails) {
    Optional<Member> opt = memberRepository.findByAppId(principalDetails.getMember().getAppId());
    Member member;
    if (opt.isPresent()) {
      member=Member.builder()
          .appId(principalDetails.getMember().getAppId())
          .appPwd(principalDetails.getMember().getAppPwd())
          .name(principalDetails.getMember().getName())
          .email(principalDetails.getMember().getEmail())
          .role(Role.MEMBER)
          .baseRequest(BaseRequest.builder()
              .constructor(principalDetails.getMember().getAppId())
              .updater(principalDetails.getMember().getAppId())
              .build())
          .refreshToken(refreshToken)
          .build();
    } else {
      member = opt.get();
      member.updateRefreshToken(refreshToken);
    }

    memberRepository.save(member);
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
  }
}
