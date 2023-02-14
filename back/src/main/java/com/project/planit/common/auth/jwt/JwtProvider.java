package com.project.planit.common.auth.jwt;

import com.project.planit.common.auth.userDetails.PrincipalDetails;
import com.project.planit.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.project.planit.common.jwt fileName       : JwtService author         : Gukss
 * date           : 2023-02-08 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-08        Gukss       최초
 * 생성
 */
@Slf4j
@Component
public class JwtProvider {
  private final String SECRET_KEY;
//  @Value("${jwt.access-token-validity-in-seconds}")

  private final Long ACCESS_TOKEN_VALIDATE_TIME; // = 1000L * 30 * 60; //30분
  public static Long REFRESH_TOKEN_VALIDATE_TIME; // = 1000L * 60 * 60 * 24 * 7; //일주일
  private final String AUTHORITIES_KEY = "role";

  public JwtProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.access-token-validity-in-seconds}") Long accessTokenValidateTime,
      @Value("${jwt.refresh-token-validity-in-seconds}") Long refreshTokenValidateTime) {
    this.ACCESS_TOKEN_VALIDATE_TIME = accessTokenValidateTime;
    this.REFRESH_TOKEN_VALIDATE_TIME = refreshTokenValidateTime;
    this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

//  public String createAccessToken(Long id, String memberAppId, String memberName, String role){
  public String createAccessToken(Authentication authentication, Long id, String memberName){
    Date now = new Date();
    Date validity = new Date(now.getTime() + ACCESS_TOKEN_VALIDATE_TIME);

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());

    String role = authentication.getAuthorities().toString();

    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    String memberAppId = principal.getUsername();
    authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    Claims claims = Jwts.claims()
        .setSubject("access token")
        .setIssuedAt(now)
        .setExpiration(validity);

    claims.put("memberId", id);
    claims.put("memberAppId", memberAppId);
    claims.put("memberName", memberName);
    claims.put("role", role);

    return Jwts.builder()
        .signWith(signatureAlgorithm, signingKey)
        .setHeaderParam("type", "jwt")
        .setClaims(claims)
        .claim(AUTHORITIES_KEY, role)
        .compact();
  }

//  public String createRefreshToken(Authentication authentication){
//    Date now = new Date();
//    Date validity = new Date(now.getTime() + REFRESH_TOKEN_VALIDATE_TIME);
//
//    return Jwts.builder()
//        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//        .setIssuedAt(now)
//        .setExpiration(validity)
//        .compact();
//  }

  public Authentication getAuthentication(String accessToken) {
    Claims claims = parseClaims(accessToken);
    String memberAppId = String.valueOf(claims.get("memberAppId"));
    String memberAppName = String.valueOf(claims.get("memberName"));
    Long memberId = Long.parseLong(String.valueOf(claims.get("memberId")));
    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    //동작안하면
//    return new UsernamePasswordAuthenticationToken(new PrincipalDetails(memberAppId, memberAppPwd, authorities);
    //로 바꾸기
//    return new UsernamePasswordAuthenticationToken(new PrincipalDetails((Member) claims.get("memberAppId")), authorities);
    // 디비를 거치지 않고 토큰에서 값을 꺼내 바로 시큐리티 유저 객체를 만들어 Authentication을 만들어 반환하기에 유저네임, 권한 외 정보는 알 수 없다.
    User principal = new User(claims.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);

  }

  public Claims parseClaims(String accessToken) {
    try {
      //토큰을 넣어서 풀어준다.
      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();
      //풀어줬으니까 값이 나온다. 클레임이 나온다.
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.info("만료된 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 토큰입니다.");
    } catch (IllegalStateException e) {
      log.info("토큰이 잘못되었습니다.");
    }

    return false;
  }

  /**
   * Claim 에서 username 가져오기
   */
  public Long getMemberIdFromToken(String token) {
    Long memberId = Long.parseLong(String.valueOf(parseClaims(token).get("memberId")));
    return memberId;
  }
}