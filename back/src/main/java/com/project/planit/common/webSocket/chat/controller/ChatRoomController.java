package com.project.planit.common.webSocket.chat.controller;

/**
 * packageName    : com.project.planit.common.webSocket.chat.controller
 * fileName       : ChatRoomController
 * author         : dongk
 * date           : 2023-02-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-05        dongk       최초 생성
 */

import com.project.planit.common.webSocket.chat.dto.ChatRoom;
import com.project.planit.common.webSocket.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅 리스트 화면
//    @GetMapping("/rooms")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
    // 모든 채팅방 목록 반환
    @GetMapping("/chatrooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/chatroom")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String roomId, String roomName) {
        return chatRoomRepository.createChatRoom(roomId, roomName);
    }
    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable Long roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
    // 특정 채팅방 조회
    @GetMapping("/chatroom/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
