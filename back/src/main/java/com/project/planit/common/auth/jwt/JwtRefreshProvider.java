package com.project.planit.common.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.project.planit.common.auth.jwt fileName       : JwtRefreshProvider author
 *      : SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */

// 리프레시 토큰 생성, 검증
// TokenProvider 기능을 확장
// 토큰 생성 시 가중치를 클레임에 넣는다.
// 토큰 검증 시 유저 가중치 > 리프레시 토큰 가중치라면 리프레시 토큰은 유효하지 않다.
@Component
public class JwtRefreshProvider {
  private final String SECRET_KEY;
  public static Long REFRESH_TOKEN_VALIDATE_TIME = 1000L * 60 * 60 * 24 * 7; //일주일

  public JwtRefreshProvider(@Value("${jwt.secret}") String secretKey) {
    this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createRefreshToken(Authentication authentication){
    Date now = new Date();
    Date validity = new Date(now.getTime() + REFRESH_TOKEN_VALIDATE_TIME);

    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();
  }
}
