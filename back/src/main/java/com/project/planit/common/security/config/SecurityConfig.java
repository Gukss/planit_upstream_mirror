package com.project.planit.common.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * packageName    : com.project.planit.common.security.config
 * fileName       : SecurityConfig
 * author         : dongk
 * date           : 2023-02-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-05        dongk       최초 생성
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationfilter jwtAuthenticationfilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtEntryPoint jwtEntryPoint;

    private final CustomUserDetailService customUserDetailService;

    //WebSecurityConfigurerAdapter 대신 사용,SecurityFilterChain Bean 등록을 통해 해결한다.
    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.cors()
                .and()
                .csrf().disable() //기본값이 on인 csrf 취약점 보안을 해제한다. on으로 설정할 경우 웹페이지에서 추가처리 필요하다.
                .authorizeRequests()
                .antMatchers("/", "/join/**", "/login").permitAll() //권한없이 접근 가능

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)

                .and()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)

                .and()
                .addfilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
                ;

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
