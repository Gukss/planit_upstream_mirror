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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * packageName    : com.project.planit.common.auth.config fileName       : SecurityConfig author
 *     : SSAFY date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        SSAFY       ??????
 * ??????
 */
// ???????????? ???????????? ????????? ?????? WebSecurityConfigurer??? implements ????????? WebSecurityConfigurerAdapter??? extends?????? ????????? ?????????
// ???????????? WebSecurityConfigurerAdapter??? extends ?????? ?????? ??????
@EnableWebSecurity // ???????????? ???????????? ??????????????????
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize ??????????????? ????????? ?????? ??????
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Configuration
public class SecurityConfig {

  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final JwtRefreshProvider jwtRefreshProvider;


  private final PrincipalDetailsService principalDetailsService;
//  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtFilter jwtFilter;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  // BCryptPasswordEncoder ?????? ???????????? ????????? ??????
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
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
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

        // ????????? ???????????? ?????? ????????? STATELESS??? ??????
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .formLogin().disable() //form login ??????
        .httpBasic().disable()

        .authorizeRequests()
        //?????????
//          .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
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
        //?????????
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers( "/api/stoarges").authenticated()
        .antMatchers( "/api/votes").authenticated()
        .antMatchers( "/api/rooms").authenticated()
        .antMatchers( "/api/rooms/users").authenticated()
        .antMatchers( "/api/chatting").authenticated()
        .antMatchers( "/api/notification").authenticated()
        .antMatchers( "/api/votes/vote-item").authenticated()
        .antMatchers( "/api/votes/vote-item/user").authenticated()
        // ?????? ?????? ????????? ??????
        .anyRequest().permitAll()

        .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
    configuration.setAllowCredentials(true);

    configuration.addExposedHeader("Authorization");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
