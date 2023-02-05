package com.project.planit.common.webSocket.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

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
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;
    public static ChatRoom create(String roomId, String name){
        return ChatRoom.builder()
                .name(name)
                .roomId(roomId)
                .build();
    }
}
