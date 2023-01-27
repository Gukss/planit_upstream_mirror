package com.project.planit.member.service;

import com.project.planit.member.dto.createMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.entity.Role;
import com.project.planit.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.project.planit.member.service fileName       : MemberService author
 * : SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  @Override

  public Long createMember(
      createMemberRequest request) {

    Member member=Member.builder()
        .appId(request.getMemberAppId())
        .appPwd(request.getMemberAppPwd())
        .name(request.getMemberName())
        .email(request.getMemberEmail())
        .role(Role.MEMBER)
        .build();

    Member newMember=memberRepository.save(member);
    return newMember.getId();
  }

  @Override
  public boolean checkMemberId(String userAppId) {
    return false;
  }

  @Override
  public boolean findMemberIdByMemberEmail(String email) {
    return false;
  }

  @Override
  public boolean updateMemberId(String userAppId) {
    return false;
  }
}
