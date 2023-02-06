package com.project.planit.common.webSocket.chat.controller;

import com.project.planit.common.webSocket.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * packageName    : com.project.planit.common.webSocket.chat.controller
 * fileName       : ChatController
 * author         : dongk
 * date           : 2023-02-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-05        dongk       최초 생성
 */
@RequiredArgsConstructor
@Controller
public class ChatController {

    //SimpMessagingTemplate은 메시지를 먼저 받지 않아도 애플리케이션 내의 어느 곳에서든지 메시지를 전송한다.
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage){
        if(ChatMessage.MessageType.ENTER.equals(chatMessage.getType())){
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/chatroom" + chatMessage.getRoomId(), chatMessage);
    }

}
