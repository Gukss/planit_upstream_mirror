package com.project.planit.vote.dto;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;

/**
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteRequest author         :
 * SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteRequest extends BaseEntity{
  private String title;
  private Room room;

  @Embedded
  private BaseRequest baseRequest;
}
