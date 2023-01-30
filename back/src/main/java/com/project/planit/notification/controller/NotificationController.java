package com.project.planit.notification.controller;

import com.project.planit.notification.dto.createNotificationRequest;
import com.project.planit.notification.service.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationServiceImpl notificationService;

    @GetMapping()
    public ResponseEntity<?> findNotification(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 알림 생성 (토큰에 sendMemberId가져와서 사용)
    @PostMapping()
    public ResponseEntity<?> createNotification(@RequestBody createNotificationRequest request){
        notificationService.createNotification(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
