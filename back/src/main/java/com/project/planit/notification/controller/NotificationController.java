package com.project.planit.notification.controller;

import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.service.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationServiceImpl notificationService;

    @GetMapping
    public ResponseEntity<?> findNotification(){
        // TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함
        return ResponseEntity.ok(notificationService.findNotification("sksn12"));
    }

    // 알림 생성
    // TODO : 헤더 토큰에 sendMemberId가져와서 사용
    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody CreateNotificationRequest request){
        notificationService.createNotification(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateNotification(@RequestBody UpdateNotificationRequest request){
        notificationService.updateNotification(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
