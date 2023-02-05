package com.project.planit.common.webSocket.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * packageName    : com.project.planit.common.webSocket.chat.dto
 * fileName       : ChattingMessage
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
public class ChatMessage {

    //메시지 타입: 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }
    @Enumerated(EnumType.STRING)
    private MessageType type; //메시지 타입
    private String roomId; //방번호
    private String sender; //메시지 보낸 사람
    private String message; //메시지
}
