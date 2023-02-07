package com.project.planit.vote.dto;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;

/**
<<<<<<< HEAD
 * packageName    : com.project.planit.vote.dto
 * fileName       : CreateVoteRequest
 * author         : Gukss
 * date           : 2023-01-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR   NOTE
 * -----------------------------------------------------------
 * 2023-01-26        Gukss       최초생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
=======
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteRequest author         :
 * Gukss date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        Gukss       최초
 * 생성
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteRequest extends BaseEntity{

  private String title;
  private Long roomId;

  @Embedded
  private BaseRequest baseRequest;
}
