package com.project.planit.common.webSocket.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.planit.common.webSocket.chat.dto.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * packageName    : com.project.planit.common.webSocket.chat.service
 * fileName       : ChatServiceImpl
 * author         : dongk
 * date           : 2023-02-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-05        dongk       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl {

    private final ObjectMapper objectMapper;
    private Map<Long, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(Long roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(Long roomId) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(roomId)
                .build();
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
