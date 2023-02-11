package com.project.planit.common.auth.config;

import com.project.planit.common.auth.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.project.planit.common.auth.config fileName       : JwtConfig author
 * : SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */
@Configuration
public class JwtConfig {
  @Value("${jwt.secret}")
  private String accessTokenSecret;
  @Value("${jwt.secret}")
  private String refreshTokenSecret;
  @Value("${jwt.access-token-validity-in-seconds}")
  private Long accessTokenValidityInSeconds;
  @Value("${jwt.refresh-token-validity-in-seconds}")
  private Long refreshTokenValidityInSeconds;

  // 액세스 토큰 발급용, 리프레시 토큰 발급용은 각각 별도의 키와 유효기간을 갖는다.
//  @Bean(name = "jwtProvider")
//  public JwtProvider jwtProvider() {
//    return new JwtProvider(accessTokenSecret);
//  }

  // 리프레시 토큰은 별도의 키를 가지기 떄문에 리프레시 토큰으로는 API 호출 불가
  // 액세스 토큰 재발급 시 검증용

}
