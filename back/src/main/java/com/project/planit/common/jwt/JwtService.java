package com.project.planit.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.project.planit.common.jwt fileName       : JwtService author         : Gukss
 * date           : 2023-02-08 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-08        Gukss       최초
 * 생성
 */
@Service
public class JwtService {
  private static final String SECRET_KEY = "planitplanitplanitplanitplanitplanitplanitplanit";

  //로그인 서비스 던질 때 같이하면된다.

  /**
   * JWT 토큰을 만드는 메소드
   *
   * @param subject String member_id 토큰을 만들때 사용한다.
   * @param expTime long 30분: 30*1000*60
   * @return 토큰을 반환한다.
   */
  public String createToken(Long id, String memberAppId, String memberName, long expTime){
    if(expTime<=0){
      throw new RuntimeException("만료시간이 0보다 커야합니다.");
    }

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());
    Date validity = new Date(System.currentTimeMillis() + expTime);

    Claims claims = Jwts.claims()
        .setSubject("access token")
        .setIssuedAt(new Date())
        .setExpiration(validity);

    claims.put("memberId", id);
    claims.put("memberAppId", memberAppId);
    claims.put("memberName", memberName);

    return Jwts.builder()
        .setHeaderParam("type", "jwt")
        .setClaims(claims)
        .signWith(signingKey, signatureAlgorithm)
        .compact();
  }

  //token 값 확인 메소드

  public Claims gettokenContents(String jwt){
    Claims claims = Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .parseClaimsJws(jwt).getBody();
    return claims;
  }

  //토큰 검증하는 메서드를 만들어서 boolean type으로 return 해서 토큰을 검증하는 메서드에서 불러서 사용하면 된다.
  //원래는 내부에서 boolean type으로 인증된 토큰이다 아니다 이렇게 만들어줘야한다.

  /**
   * 토큰을 검증하는 메소드
   *
   * @param token String jwt토큰
   * @return boolean t,f
   */
  public boolean validateToken(String token){
    try{
      Jws<Claims> claims  = Jwts.parser()
          .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
          //토큰을 넣어서 풀어준다.
          .parseClaimsJws(token);
      //풀어줬으니까 값이 나온다. 클레임이 나온다.
      return !claims.getBody()
          .getExpiration()
          .before(new Date());
    }catch(ExpiredJwtException e){
      //todo: logger 적어주기
      return false;
    }catch(JwtException e){
      //todo: logger 적어주기
      return false;
    }
  }

  /**
   * 토큰의 Claim 디코딩
   */
  private Claims getAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Claim 에서 username 가져오기
   */
  public Long getMemberIdFromToken(String token) {
    Long memberId = Long.parseLong(String.valueOf(getAllClaims(token).get("memberId")));
    return memberId;
  }
}