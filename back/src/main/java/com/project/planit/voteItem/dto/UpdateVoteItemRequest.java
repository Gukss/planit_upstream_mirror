package com.project.planit.voteItem.dto;

import com.project.planit.util.BaseRequest;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.voteItem.dto fileName       : UpdateVoteItemRequest author
 *      : Gukss date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        Gukss       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class UpdateVoteItemRequest {

  private Long voteItemId;
  private String newVoteItemName;
  @Embedded
  private BaseRequest baseRequest;
}
