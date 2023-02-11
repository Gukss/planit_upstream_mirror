package com.project.planit.notification.controller;

import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.service.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationServiceImpl notificationService;


    // SSE객체 생성을 위한 컨트롤러
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        Long memberId=1L;
        return notificationService.subscribe(memberId,lastEventId);
    }

    @GetMapping
    public ResponseEntity<List<FindNotificationResponse>> findNotification(){
        // todo : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함
        Long id=1L;
        List<FindNotificationResponse> response=notificationService.findNotification("sksn12",id);
        return ResponseEntity.ok(response);
    }

    // 알림 생성
    // TODO : 헤더 토큰에 sendMemberId가져와서 사용
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody List<CreateNotificationRequest> request){
        notificationService.createNotification(request);

        for (CreateNotificationRequest notificationItem:request){
            System.out.println("컨트롤러의 for문");
            // value header에서 받아오는 id값으로 변경
            notificationService.send(1L, "youngman님이 초대 하셨습니다","message");
        }

        return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<String> updateNotification(@RequestBody UpdateNotificationRequest request){
        notificationService.updateNotification(request);
        return ResponseEntity.ok("ok");
    }
}
