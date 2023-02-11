package com.project.planit.voteItemMember.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.voteItemMember.dto fileName       : findVoteItemMember author
 *         : Gukss date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        Gukss       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class FindVoteItemMemberResponse {
  private Long memberId;
  private Long voteItemId;
}
