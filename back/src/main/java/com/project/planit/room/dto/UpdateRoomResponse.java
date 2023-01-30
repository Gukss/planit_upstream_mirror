package com.project.planit.room.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.room.dto fileName       : UpdateRoomResponse author         :
 * SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UpdateRoomResponse {
  Long roomId;
  String roomName;
  LocalDate startDate;
  LocalDate endDate;

  public static UpdateRoomResponse create(Long roomId, String roomName, LocalDate startDate, LocalDate endDate){
    return UpdateRoomResponse.builder()
        .roomId(roomId)
        .roomName(roomName)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }
}
