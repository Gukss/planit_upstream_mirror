package com.project.planit.vote.dto;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import lombok.Data;

/**
 * packageName    : com.project.planit.vote.dto fileName       : CreateVoteRequest author         :
 * SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@Data
public class CreateVoteRequest {
  private String title;
  private Room room;
  private BaseEntity baseEntity;
}
