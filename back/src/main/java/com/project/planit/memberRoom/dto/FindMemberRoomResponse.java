package com.project.planit.memberRoom.dto;


import lombok.*;

/**
 * packageName    : com.project.planit.memberRoom.dto
 * fileName       : FindMemberRoomResponse
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
public class FindMemberRoomResponse {
//    private String colorCode;
    private Boolean participation;
    private String colorCode;
    private Long memberId;
    private Long roomId;

}
