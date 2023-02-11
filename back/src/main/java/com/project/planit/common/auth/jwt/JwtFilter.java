package com.project.planit.common.auth.jwt;

import com.project.planit.common.auth.userDetails.PrincipalDetails;
import io.jsonwebtoken.ExpiredJwtException;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * packageName    : com.project.planit.common.auth.jwt fileName       : JwtFilter author         :
 * SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtProvider jwtProvider;

  // 실제 필터링 로직은 doFilter 안에 들어가게 된다. GenericFilterBean을 받아 구현
  // Dofilter는 토큰의 인증정보를 SecurityContext 안에 저장하는 역할 수행
  // 현재는 jwtFilter 통과 시 loadUserByUsername을 호출하여 디비를 거치지 않으므로 시큐리티 컨텍스트에는 엔티티 정보를 온전히 가지지 않는다
  // 즉 loadUserByUsername을 호출하는 인증 API를 제외하고는 유저네임, 권한만 가지고 있으므로 Account 정보가 필요하다면 디비에서 꺼내와야함
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    log.info("jwt filter");
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    try{
      String jwt = parseBearerToken(httpServletRequest);
      log.info("jwt:{}", jwt);
      String requestURI = httpServletRequest.getRequestURI();
      // 유효성 검증
      if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
        // 토큰에서 유저네임, 권한을 뽑아 스프링 시큐리티 유저를 만들어 Authentication 반환
        Authentication authentication = jwtProvider.getAuthentication(jwt);
//        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 해당 스프링 시큐리티 유저를 시큐리티 건텍스트에 저장, 즉 디비를 거치지 않음
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
      } else {
        log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
    }

//    catch(ExpiredJwtException e){
//      servletRequest.setAttribute("exception", JwtExceptionCode.EXPIRE.name());
//    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  // 헤더에서 토큰 정보를 꺼내온다.
  private String parseBearerToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    Cookie newCookie = null;
    for(Cookie x: cookies) {
      if (x.getName().equals("access")) {
        newCookie = x;
      }
    }
    String bearerToken = newCookie.getValue();
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
