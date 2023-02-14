package com.project.planit.chatting.controller;

import com.project.planit.chatting.dto.CreateChattingMessageRequest;
import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.dto.FindChattingMessageResponse;
import com.project.planit.chatting.entity.ChattingMessage;
import com.project.planit.chatting.service.ChattingServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting")
public class ChattingController {
    private final ChattingServiceImpl chattingService;
    private final SimpMessageSendingOperations messagingTemplate;

    // pub, sub관리 컨트롤러 RequestMapping 무시..
    @MessageMapping("/message")
    public void message(CreateChattingMessageRequest chatMessage){
        messagingTemplate.convertAndSend("/sub/room/" + chatMessage.getRoomId(), chatMessage);
    }

    @PostMapping
    public ResponseEntity<?> createChattingMessage(@RequestBody CreateChattingRequest request){
        //todo: memberId 토큰값으로 바꿔주기

        Long memberId = 1L;
        chattingService.createChattingMessage(request,memberId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path = "/{roomId}")
    public ResponseEntity<List<FindChattingMessageResponse>> findChattingMessage(@PathVariable Long roomId){
        //todo: memberId 토큰 값으로 변경하기
        Long memberId = 1L;
        List<ChattingMessage> foundChattingMessage = chattingService.findChattingMessage(memberId, roomId);
        List<FindChattingMessageResponse> resList = new ArrayList<>();

        for(ChattingMessage chattingMessage: foundChattingMessage){
            resList.add(chattingMessage.createFindChattingMessageResponse());
        }

        return ResponseEntity.ok().body(resList);
    }

}
