package com.project.planit.memberRoom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.memberRoom.dto
 * fileName       : UpdateMemberRoomResponse
 * author         : dongk
 * date           : 2023-02-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-04        dongk       최초 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UpdateMemberRoomResponse {
    private Long roomId;
    private Boolean participation;
    private Long memberId;
}
