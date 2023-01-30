package com.project.planit.member.service;

import com.project.planit.member.dto.createMemberRequest;
import com.project.planit.member.dto.updateMemberRequest;
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
  Long createMember(createMemberRequest request);
  Member updateMember(Long id,updateMemberRequest request);
  boolean existMemberId(String memberAppId);
  Member readMember(Long memberAppId);
  List<Member> readMemberListByMemberId(String memberAppId);
  String findMemberIdByMemberEmail(String email);
  String findMemberPwdByMemberIdAndMemberEmail(String memberAppId,String email);

}
