//package com.project.planit.common.auth.jwt;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// * packageName    : com.project.planit.common.jwt fileName       : JwtAuthenticationFilter author
// *      : SSAFY date           : 2023-02-09 description    :
// * =========================================================== DATE              AUTHOR
// * NOTE ----------------------------------------------------------- 2023-02-09        SSAFY       최초
// * 생성
// */
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//  private final JwtProvider jwtProvider;
//  public static final String AUTHORIZATION_HEADER = "Authorization";
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    log.info("request method: {}", request.getMethod());
//    log.info("jwt filter");
//    String token = parseBearerToken(request);
//
//    try {
//      if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
//        Authentication authentication = jwtProvider.getAuthentication(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        log.info("{}의 인증정보 저장", authentication.getName());
//      } else {
//        log.info("유요한 JWT 토큰이 없습니다.");
//      }
//    } catch (ExpiredJwtException e) {
//      request.setAttribute("exception", JwtExceptionCode.EXPIRE.name());
//    }
//
//    filterChain.doFilter(request, response);
//  }
//
//  private String parseBearerToken(HttpServletRequest request) {
//    String bearerToken = request.getHeader("Authorization");
//
//    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//      return bearerToken.substring((7));
//    }
//
//    return null;
//  }
//
//}