package com.project.planit.member.service;

import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.dto.ReadMemberResponse;
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
  void updateMember(Long id, UpdateMemberRequest request);
  boolean existMemberId(String memberAppId);
  ReadMemberResponse readMember(Long memberId);
  List<ReadMemberResponse> readMemberListByMemberId(String memberAppId);
  String findMemberIdByMemberEmail(String email);
  String findMemberPwdByMemberIdAndMemberEmail(String memberAppId,String email);

}
