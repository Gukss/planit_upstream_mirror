package com.project.planit.common.auth.userDetails;

import com.project.planit.member.entity.Member;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * packageName    : com.project.planit.common.auth fileName       : AuthUser author         : Gukss
 * date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        Gukss       최초
 * 생성
 */
@Data
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {
  private final Member member;
//  private final String memberAppId;
//  private final String memberAppName;

  //todo: 맞는지 확인 필요
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority((member.getRole().toString())));
    return authorities;
  }

  @Override
  public String getPassword() {
    return member.getAppPwd();
  }

  @Override
  public String getUsername() {
    return member.getAppId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
