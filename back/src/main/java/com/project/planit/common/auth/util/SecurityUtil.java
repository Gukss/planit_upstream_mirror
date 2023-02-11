package com.project.planit.common.auth.util;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * packageName    : com.project.planit.common.auth.util fileName       : SecurityUtil author
 * : SSAFY date           : 2023-02-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-10        SSAFY       최초
 * 생성
 */
@Slf4j
public class SecurityUtil {
  // 인증 API를 제외하고는 loadUserByUsername를 호출하지 않기에 별도로 Account를 디비에서 조회해주어야 한다.
  // security context의 Authentication 객체를 이용해 username을 리턴해준다.
  // security context에 authentication 객체가 저장되는 시점은 JwtFilter의 doFilter 영역
  public static Optional<String> getCurrentUsername() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      log.debug("Security Context에 인증 정보가 없습니다.");
      return Optional.empty();
    }

    String username = null;
    if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
      username = springSecurityUser.getUsername();
    } else if (authentication.getPrincipal() instanceof String) {
      username = (String) authentication.getPrincipal();
    }

    return Optional.ofNullable(username);
  }
}
