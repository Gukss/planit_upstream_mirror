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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class UpdateRoomResponse {
  private Long roomId;
  private String roomName;
  private LocalDate startDate;
  private LocalDate endDate;

  public static UpdateRoomResponse create(Long roomId, String roomName, LocalDate startDate, LocalDate endDate){
    return UpdateRoomResponse.builder()
        .roomId(roomId)
        .roomName(roomName)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }
}
