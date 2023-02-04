package com.project.planit.memberRoom.dto;

import lombok.*;

/**
 * packageName    : com.project.planit.memberRoom.dto
 * fileName       : CreateMemberRoomResponse
 * author         : dongk
 * date           : 2023-02-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-04        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateMemberRoomResponse {
    private Long roomId;
}
