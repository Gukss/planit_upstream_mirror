package com.project.planit.voteItemMember.dto;

import com.project.planit.voteItem.dto.CreateVoteItemResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.voteItemMember.dto fileName       :
 * CreateVoteItemMemberResponse author         : Gukss date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        Gukss       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteItemMemberResponse {
  private Long memberId;

  private Long voteItemId;

  public static CreateVoteItemMemberResponse create(Long memberId, Long voteItemId){
    return CreateVoteItemMemberResponse.builder()
        .memberId(memberId)
        .voteItemId(voteItemId)
        .build();
  }
}
