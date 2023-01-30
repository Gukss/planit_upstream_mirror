package com.project.planit.member.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.dto.createMemberRequest;
import com.project.planit.member.dto.updateMemberRequest;

import com.project.planit.member.entity.Member;
import com.project.planit.member.entity.Role;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import java.util.List;
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
  public Long createMember(createMemberRequest request) {
    Member member=Member.create(request);
    Member newMember=memberRepository.save(member);
    return newMember.getId();
  }

  @Transactional
  @Override
  public Member updateMember(Long id,updateMemberRequest request) {
    Member member= memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

    member.update(request);
    return member;
  }

  @Override
  public boolean existMemberId(String memberAppId) {
    if(memberRepository.findByAppId(memberAppId).isPresent()){
      return true;
      // 멤버 아이디가 중복이면 true반환
    }
    return false;
  }

  @Override
  public Member readMember(Long memberAppId) {
    Member member=memberRepository.findById(memberAppId)
        .orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
    return member;
  }

  @Override
  public List<Member> readMemberListByMemberId(String memberAppId) {
    List<Member> memberList=memberRepository.findAllByAppId(memberAppId);
    return memberList;
  }

  @Override
  public String findMemberIdByMemberEmail(String email) {
    Member member=memberRepository.findByEmail(email)
        .orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
    return member.getAppId();
  }

  @Override
  public String findMemberPwdByMemberIdAndMemberEmail(String memberAppId,String email) {
    Member member= memberRepository.findByAppIdAndEmail(memberAppId,email)
        .orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
    return member.getAppPwd();
  }


}
