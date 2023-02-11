package com.project.planit.common.auth.config;

import com.project.planit.common.auth.jwt.JwtAccessDeniedHandler;
import com.project.planit.common.auth.jwt.JwtAccessSuccessHandler;
import com.project.planit.common.auth.jwt.JwtAuthenticationEntryPoint;
import com.project.planit.common.auth.jwt.JwtFilter;
import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.common.auth.jwt.JwtRefreshProvider;
import com.project.planit.common.auth.userDetails.PrincipalDetailsService;
import com.project.planit.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * packageName    : com.project.planit.common.auth.config fileName       : SecurityConfig author
 *     : SSAFY date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        SSAFY       최초
 * 생성
 */
// 추가적인 시큐리티 설정을 위해 WebSecurityConfigurer를 implements 하거나 WebSecurityConfigurerAdapter를 extends하는 방법이 있는데
// 여기서는 WebSecurityConfigurerAdapter를 extends 하는 방법 사용
@EnableWebSecurity // 기본적인 웹보안을 활성화하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션 사용을 위해 선언
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Configuration
public class SecurityConfig {

  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final JwtRefreshProvider jwtRefreshProvider;
  private final CorsFilter corsFilter;


  private final PrincipalDetailsService principalDetailsService;
//  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtFilter jwtFilter;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  // BCryptPasswordEncoder 라는 패스워드 인코더 사용
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() { //todo: 밑에 인자들 생각해보기
    return (web) -> web.ignoring()
        .antMatchers(
            "/h2/**"
            ,"/favicon.ico"
            ,"/error"
                ,"/member"
        );
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new JwtAccessSuccessHandler(memberRepository, jwtProvider, jwtRefreshProvider);
  }

  @Bean
  @Order(SecurityProperties.BASIC_AUTH_ORDER)
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .formLogin().disable() //form login 안함
        .httpBasic().disable()

        .authorizeRequests()
        //로컬용
//        .antMatchers( "/stoarges").authenticated()
//        .antMatchers( "/votes").authenticated()
//        .antMatchers( "/rooms").authenticated()
//        .antMatchers( "/rooms").authenticated()
//        .antMatchers( "/rooms/users").authenticated()
//        .antMatchers( "/chatting").authenticated()
//        .antMatchers( "/notification").authenticated()
//        .antMatchers( "/votes/vote-item").authenticated()
//        .antMatchers( "/votes/vote-item/user").authenticated()
//            .antMatchers(HttpMethod.POST, "/members").permitAll()
//            .antMatchers(HttpMethod.POST, "/sign-in").permitAll()
        //서버용
        .antMatchers( "/api/stoarges").authenticated()
        .antMatchers( "/api/votes").authenticated()
        .antMatchers( "/api/rooms").authenticated()
        .antMatchers( "/api/rooms").authenticated()
        .antMatchers( "/api/rooms/users").authenticated()
        .antMatchers( "/api/chatting").authenticated()
        .antMatchers( "/api/notification").authenticated()
        .antMatchers( "/api/votes/vote-item").authenticated()
        .antMatchers( "/api/votes/vote-item/user").authenticated()
        // 그외 모든 요청은 허용
        .anyRequest().permitAll()

        .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
//    http.authorizeHttpRequests()
//        // POST /users는 인증이 되어야 접근 가능
//        .antMatchers( "/api/stoarges").authenticated()
//        .antMatchers( "/api/votes").authenticated()
//        .antMatchers( "/api/rooms").authenticated()
//        .antMatchers( "/api/rooms").authenticated()
//        .antMatchers( "/api/rooms/users").authenticated()
//        .antMatchers( "/api/chatting").authenticated()
//        .antMatchers( "/api/notification").authenticated()
//        .antMatchers( "/api/votes/vote-item").authenticated()
//        .antMatchers( "/api/votes/vote-item/user").authenticated()
//        // 그외 모든 요청은 허용
//        .anyRequest().permitAll()
//        .and()
//        .logout()
//        .logoutSuccessUrl("/");
////        .and()
////        .oauth2Login()
////        .successHandler(authenticationSuccessHandler())
////        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정을 저장
////        .userService(principalDetailsService); // OAuth2 로그인 성공 시, 후작업을 진행할 UserService 인터페이스 구현체 등록
//
//    // jwt 사용을 위해 session 해제
//    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
////        .addFilter(corsFilter)
//        .formLogin().disable() //form login 안함
//        .httpBasic().disable();
//
//    // jwt 필터 추가
//    //addFilterBefore(new JwtFilterConfig(jwtService), ...)
//    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//    // jwt 인증 실패시 exception handler 등록
//    http.exceptionHandling()
//        .accessDeniedHandler(jwtAccessDeniedHandler)
//        .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//
//    return http.build();
  }
}
