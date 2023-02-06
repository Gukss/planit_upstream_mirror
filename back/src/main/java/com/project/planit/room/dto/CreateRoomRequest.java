package com.project.planit.room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
}
