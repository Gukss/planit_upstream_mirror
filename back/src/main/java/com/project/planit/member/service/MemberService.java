package com.project.planit.member.service;

import com.project.planit.member.dto.createMemberRequest;

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
  boolean checkMemberId(String userAppId);
  boolean findMemberIdByMemberEmail(String email);
  boolean updateMemberId(String userAppId);

}
