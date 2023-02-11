package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : CreateVoteResponse
 * author         : Gukss date           : 2023-01-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-01-26        Gukss       최초생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class CreateVoteResponse {
  private Long voteId;

  public static CreateVoteResponse create(Long voteId){
    CreateVoteResponse createVoteResponse = CreateVoteResponse.builder()
        .voteId(voteId)
        .build();
    return createVoteResponse;
  }
}
