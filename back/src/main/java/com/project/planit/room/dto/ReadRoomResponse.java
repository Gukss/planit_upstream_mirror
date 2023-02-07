package com.project.planit.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.time.LocalDate;

=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
/**
 * packageName    : com.project.planit.room.dto fileName       : ReadRoomResponse author         :
 * SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@Data
@AllArgsConstructor
<<<<<<< HEAD
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
=======
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ReadRoomResponse {

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
