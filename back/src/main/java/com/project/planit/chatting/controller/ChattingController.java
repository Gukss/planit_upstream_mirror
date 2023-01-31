package com.project.planit.chatting.controller;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.service.ChattingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting")
public class ChattingController {
    private final ChattingServiceImpl chattingService;

    @PostMapping
    public ResponseEntity<?> createChattingMessage(@RequestBody CreateChattingRequest request){
        chattingService.createChattingMessage(request,1L);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path = "/{roomId}")
    public ResponseEntity<?> findChattingMessage(@PathVariable Long roomId){
        return ResponseEntity.ok(chattingService.findChattingMessage(1L,roomId));
    }

}
