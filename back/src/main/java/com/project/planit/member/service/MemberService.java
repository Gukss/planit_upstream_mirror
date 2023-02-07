package com.project.planit.member.service;

import com.project.planit.member.dto.CreateMemberRequest;
<<<<<<< HEAD
import com.project.planit.member.dto.ReadMemberResponse;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.member.dto.UpdateMemberRequest;
import com.project.planit.member.entity.Member;
import java.util.List;

/**
 * packageName    : com.project.planit.member.service
 * fileName       : MemberService
 * author         : SSAFY
 * date           : 2023-01-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR                  NOTE
 * -----------------------------------------------------------
 * 2023-01-26        youngman                    최초
 * 생성
 */
public interface MemberService {
  Long createMember(CreateMemberRequest request);
<<<<<<< HEAD
  void updateMember(Long id, UpdateMemberRequest request);
  boolean existMemberId(String memberAppId);
  ReadMemberResponse readMember(Long memberAppId);
  List<ReadMemberResponse> readMemberListByMemberId(String memberAppId);
=======
  Member updateMember(Long id, UpdateMemberRequest request);
  boolean existMemberId(String memberAppId);
  Member readMember(Long memberAppId);
  List<Member> readMemberListByMemberId(String memberAppId);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  String findMemberIdByMemberEmail(String email);
  String findMemberPwdByMemberIdAndMemberEmail(String memberAppId,String email);

}
