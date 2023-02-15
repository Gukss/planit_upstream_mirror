package com.project.planit.storage.controller;

import com.project.planit.storage.dto.SocketScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.storage.controller fileName       : SocketScheduleController
 * author         : SSAFY date           : 2023-02-15 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-15        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
public class SocketScheduleController {
  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/schedule")
  public void schedule(SocketScheduleRequest socketScheduleRequest){
    messagingTemplate.convertAndSend("/sub/schedule/" + socketScheduleRequest.getRoomId(), socketScheduleRequest);
  }

}
