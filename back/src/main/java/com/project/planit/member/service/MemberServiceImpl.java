package com.project.planit.member.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundMemberException;
import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.dto.ReadMemberResponse;
=======
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.dto.CreateMemberRequest;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.member.dto.UpdateMemberRequest;

import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
  public Long createMember(CreateMemberRequest request) {
<<<<<<< HEAD
    // @TODO : 토큰으로 던져줘야함
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    Member member=Member.create(request);
    Member newMember=memberRepository.save(member);
    return newMember.getId();
  }

  @Transactional
  @Override
<<<<<<< HEAD
  public void updateMember(Long id, UpdateMemberRequest request) {
    Member member= memberRepository.findById(id)
        .orElseThrow(() -> new NotFoundMemberException(NotFoundExceptionMessage.USER_NOT_FOUND));

    member.update(request);
=======
  public Member updateMember(Long id, UpdateMemberRequest request) {
    Member member= memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

    member.update(request);
    return member;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  @Override
  public boolean existMemberId(String memberAppId) {
    if(memberRepository.findByAppId(memberAppId).isPresent()){
<<<<<<< HEAD
      // 멤버 아이디가 중복이면 false반환
      return false;
    }
    return true;
  }

  @Override
  public ReadMemberResponse readMember(Long memberAppId) {
    Member member=memberRepository.findById(memberAppId)
        .orElseThrow(() -> new NotFoundMemberException(NotFoundExceptionMessage.USER_NOT_FOUND));

    ReadMemberResponse response=ReadMemberResponse.builder()
        .appId(member.getAppId())
        .name(member.getName())
        .email(member.getEmail())
        .build();

    return response;
  }

  @Override
  public List<ReadMemberResponse> readMemberListByMemberId(String memberAppId) {
    List<Member> memberList=memberRepository.findAllByAppId(memberAppId)
        .orElseThrow(() -> new NotFoundMemberException(NotFoundExceptionMessage.USER_LIST_NOT_FOUND));

    List<ReadMemberResponse> response=new ArrayList<>();

    for(Member member:memberList){
      ReadMemberResponse readMemberResponse=ReadMemberResponse.builder()
          .appId(member.getAppId())
          .name(member.getName())
          .email(member.getEmail())
          .build();

      response.add(readMemberResponse);
    }

    return response;
=======
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
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  @Override
  public String findMemberIdByMemberEmail(String email) {
    Member member=memberRepository.findByEmail(email)
<<<<<<< HEAD
        .orElseThrow(()->new NotFoundMemberException(NotFoundExceptionMessage.USER_NOT_FOUND));
=======
        .orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    return member.getAppId();
  }

  @Override
  public String findMemberPwdByMemberIdAndMemberEmail(String memberAppId,String email) {
    Member member= memberRepository.findByAppIdAndEmail(memberAppId,email)
<<<<<<< HEAD
        .orElseThrow(()->new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_EMAIL_FIND_NOT_FOUND));
    return member.getAppPwd();
  }

=======
        .orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
    return member.getAppPwd();
  }


>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
