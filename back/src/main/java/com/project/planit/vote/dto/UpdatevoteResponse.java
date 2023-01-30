package com.project.planit.vote.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.vote.dto fileName       : UpdatevoteResponse author         :
 * SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class UpdatevoteResponse {
  private Long voteId;
  private String newTitle;

  public static UpdatevoteResponse create(Long voteId, String newTitle){
    return UpdatevoteResponse.builder()
        .voteId(voteId)
        .newTitle(newTitle)
        .build();
  }
}
