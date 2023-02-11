package com.project.planit.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.room.dto fileName       : CreateRoomResponse author         :
 * Gukss date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        Gukss       최초
 * 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreateRoomResponse {
  private Long roomId;

  public static CreateRoomResponse create(Long roomId){
    CreateRoomResponse createRoomResponse = CreateRoomResponse.builder()
        .roomId(roomId)
        .build();
    return createRoomResponse;
  }
}
