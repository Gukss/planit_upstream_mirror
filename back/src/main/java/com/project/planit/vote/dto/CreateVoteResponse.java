package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteResponse author         :
 * SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@Data

public class CreateVoteResponse {
  private Vote vote;

  public CreateVoteResponse(Vote vote) {
    this.vote = vote;
  }
}
