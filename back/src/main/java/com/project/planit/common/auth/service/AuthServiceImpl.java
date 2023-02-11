package com.project.planit.common.auth.service;

import com.project.planit.common.auth.dto.ResponseAuth;
import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.common.auth.jwt.JwtRefreshProvider;
import com.project.planit.common.auth.userDetails.PrincipalDetails;
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundMemberException;
import com.project.planit.member.dto.SignInMemberResponse;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.project.planit.common.auth.service fileName       : AuthService author
 *   : SSAFY date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        SSAFY       최초
 * 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final JwtRefreshProvider jwtRefreshProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  // username 과 패스워드로 사용자를 인증하여 액세스토큰을 반환한다.
  public ResponseAuth createAccessToken(String memberAppId, String memberAppPwd) {
    // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken 객체 생성
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(memberAppId, memberAppPwd);

    // authenticationToken 객체를 통해 Authentication 객체 생성
    // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    // 그 객체를 시큐리티 컨텍스트에 저장
    SecurityContextHolder.getContext().setAuthentication(authentication);
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Member member = principal.getMember();
    // 인증 정보를 기준으로 jwt access 토큰 생성
    String accessToken = jwtProvider.createAccessToken(authentication, member.getId(), member.getName());
    String refreshToken = jwtRefreshProvider.createRefreshToken(authentication);
    //db에 저장
    member.updateRefreshToken(refreshToken);

    return ResponseAuth.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Transactional(readOnly = true)
  @Override
  public ResponseAuth refreshToken(String refreshToken) {
    // 먼저 리프레시 토큰을 검증한다.
    if(!jwtProvider.validateToken(refreshToken)) throw new RuntimeException("유효하지 않은 토큰입니다.");

    // 리프레시 토큰 값을 이용해 사용자를 꺼낸다.
    // refreshTokenProvider과 TokenProvider는 다른 서명키를 가지고 있기에 refreshTokenProvider를 써야함
    Authentication authentication = jwtProvider.getAuthentication(refreshToken);

    Member member = memberRepository.findByAppId(authentication.getName())
        .orElseThrow(()-> new NotFoundMemberException(NotFoundExceptionMessage.USER_NOT_FOUND));

    // 리프레시 토큰에 담긴 값을 그대로 액세스 토큰 생성에 활용한다.
    String accessToken = jwtProvider.createAccessToken(authentication, member.getId(), member.getName());
    // 새로 만든 액세스 토큰을 반환한다.
    return ResponseAuth.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  //todo: 사용안함
  public String reissueAccessToken(String oldAccessToken, String refreshToken) {
    if (!jwtProvider.validateToken(refreshToken)) {
      throw new RuntimeException("invalid refresh token");
    }

    Authentication authentication = jwtProvider.getAuthentication(oldAccessToken);
    Member member = ((PrincipalDetails) authentication.getPrincipal()).getMember();

    log.info("access token reissue 대상: {}", member);

    Member findMember = memberRepository.findByAppId(member.getAppId())
        .orElseThrow(() -> new RuntimeException("Not found user"));

    if (!refreshToken.equals(findMember.getRefreshToken())) {
      throw new RuntimeException("invalid refresh token");
    }

    return jwtProvider.createAccessToken(authentication, findMember.getId(), findMember.getName());
  }

  @Override
  public SignInMemberResponse createSignInMemberResponse(String accessToken) {
    Claims claims = jwtProvider.parseClaims(accessToken);
    Long memberId = Long.parseLong(String.valueOf(claims.get("memberId")));
    String memberAppId = String.valueOf(claims.get("memberAppId"));
    String memberName = String.valueOf(claims.get("memberName"));
    return SignInMemberResponse.builder()
        .token(accessToken)
        .memberId(memberId)
        .memberAppId(memberAppId)
        .memberName(memberName)
        .build();
  }
}
