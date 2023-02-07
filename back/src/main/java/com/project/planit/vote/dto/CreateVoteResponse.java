package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
<<<<<<< HEAD
 * packageName    : com.project.planit.vote.dto
 * fileName       : CreateVoteResponse
 * author         : Gukss date           : 2023-01-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-01-26        Gukss       최초생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
=======
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteResponse author         :
 * Gukss date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        Gukss       최초
 * 생성
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreateVoteResponse {
<<<<<<< HEAD
  private Long voteId;

  public static CreateVoteResponse create(Long voteId){
    CreateVoteResponse createVoteResponse = CreateVoteResponse.builder()
        .voteId(voteId)
=======
  private Vote vote;

  public static CreateVoteResponse create(Vote vote){
    CreateVoteResponse createVoteResponse = CreateVoteResponse.builder()
        .vote(vote)
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
        .build();
    return createVoteResponse;
  }
}
