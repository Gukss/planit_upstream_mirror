package com.project.planit.memberRoom.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.memberRoom.dto fileName       :
 * FindMemberRoomByRoomIdResponse author         : SSAFY date           : 2023-02-14 description
 * : =========================================================== DATE              AUTHOR
 *  NOTE ----------------------------------------------------------- 2023-02-14        SSAFY
 * 최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class FindMemberRoomByRoomIdResponse {
  private Long memberId;
  private String memberName;
}
