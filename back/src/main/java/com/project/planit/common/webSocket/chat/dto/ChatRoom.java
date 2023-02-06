package com.project.planit.common.webSocket.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.common.webSocket.chat.dto
 * fileName       : ChatRoom
 * author         : dongk
 * date           : 2023-02-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-05        dongk       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    private Long roomId;

    public static ChatRoom create(Long roomId){
        return ChatRoom.builder()
                .roomId(roomId)
                .build();
    }
}
