package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteResponse author         :
 * Gukss date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        Gukss       최초
 * 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreateVoteResponse {
  private Vote vote;

  public static CreateVoteResponse create(Vote vote){
    CreateVoteResponse createVoteResponse = CreateVoteResponse.builder()
        .vote(vote)
        .build();
    return createVoteResponse;
  }
}
