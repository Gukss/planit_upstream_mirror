package com.project.planit.common.auth.util;

import com.project.planit.common.auth.jwt.JwtProvider;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

/**
 * packageName    : com.project.planit.common.auth.util fileName       : CookieUtils author
 * : Gukss date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        Gukss       최초
 * 생성
 */
public class CookieUtils {
  public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(name)) {
          return Optional.of(cookie);
        }
      }
    }

    return Optional.empty();
  }

  public static void addCookie(HttpServletResponse response, String accessToken) {
    ResponseCookie cookie = null;
    try {
      cookie = ResponseCookie.from("access", URLEncoder.encode(accessToken, "UTF-8"))
          .httpOnly(true)
          .maxAge(JwtProvider.REFRESH_TOKEN_VALIDATE_TIME)
          .path("/")
          .build();
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }

    response.addHeader("Set-Cookie", cookie.toString());
  }
}
