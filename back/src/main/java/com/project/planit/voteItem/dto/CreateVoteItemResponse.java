package com.project.planit.voteItem.dto;

import com.project.planit.voteItem.entity.VoteItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.voteItem.dto fileName       : CreateVoteItemResponse author
 *       : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteItemResponse {
  private Long voteItemId;
  private String voteItemName;

  public static CreateVoteItemResponse create(Long voteItemId, String voteItemName){
    return CreateVoteItemResponse.builder()
        .voteItemName(voteItemName)
        .voteItemId(voteItemId)
        .build();
  }
}
