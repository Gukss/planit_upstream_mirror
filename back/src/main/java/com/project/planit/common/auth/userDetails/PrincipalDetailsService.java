package com.project.planit.common.auth.userDetails;

import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundMemberException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.project.planit.common.auth.userDetails fileName       :
 * PrincipalDetailsService author         : Gukss date           : 2023-02-09 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-09        Gukss       최초
 * 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;
  //시큐리티 session -> Authentication -> UserDetails
  //시큐리티 세션(내부 Authentication(내부 UserDetails(PrincipalDetails)))
  @Override
  public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByAppId(username)
        .orElseThrow(()->new NotFoundMemberException(NotFoundExceptionMessage.USER_NOT_FOUND));
    member.updatePwd("{noop}"+member.getAppPwd()); //There is no PasswordEncoder mapped for the id "null"때문에 형식을 지정해줌
//    return new PrincipalDetails(member.getId(), member.getAppId(), member.getName(), member.getAppPwd(), member.getRole(), member.getEmail(), member.getBaseRequest(), member.getRefreshToken());
    return new PrincipalDetails(member);
  }

  private Member save(PrincipalDetails principalDetails) {
    Member member = Member.of(principalDetails);
//    memberRepository.findByEmail(member.getAppId()).ifPresent(entity -> member.(entity.getId()));
    return memberRepository.save(member);
  }
}
