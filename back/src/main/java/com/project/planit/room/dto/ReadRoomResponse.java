package com.project.planit.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * packageName    : com.project.planit.room.dto fileName       : ReadRoomResponse author         :
 * SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class ReadRoomResponse {
    private Long roomId;
    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ReadRoomResponse create(Long roomId, String roomName, LocalDate startDate, LocalDate endDate){
        return ReadRoomResponse.builder()
                .roomId(roomId)
                .roomName(roomName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
