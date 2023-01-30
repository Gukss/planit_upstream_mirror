package com.project.planit.room.dto;

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
    LocalDate startDate;
    LocalDate endDate;
    String roomName;

    @Embedded
    private BaseRequest baseRequest;
}
