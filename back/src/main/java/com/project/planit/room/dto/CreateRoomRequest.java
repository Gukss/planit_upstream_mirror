package com.project.planit.room.dto;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDate;

/**
 * packageName    : com.project.planit.room.dto
 * fileName       : CreateRoomRequest
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateRoomRequest extends BaseEntity {
<<<<<<< HEAD
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;
    private String roomName;

    @Embedded
    private BaseRequest baseRequest;

    public static CreateRoomRequest create(LocalDate startDate, LocalDate endDate, String roomName, BaseRequest baseRequest) {
        return CreateRoomRequest.builder()
                .roomName(roomName)
                .startDate(startDate)
                .endDate(endDate)
                .baseRequest(baseRequest)
                .build();
    }
=======
    LocalDate startDate;
    LocalDate endDate;
    String roomName;

    @Embedded
    private BaseRequest baseRequest;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
