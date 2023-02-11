package com.project.planit.notification.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.service.NotificationServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationServiceImpl notificationService;
    private final JwtProvider jwtProvider;


    // SSE객체 생성을 위한 컨트롤러
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        Long memberId=1L;
        return notificationService.subscribe(memberId,lastEventId);
    }

    @GetMapping
    public ResponseEntity<List<FindNotificationResponse>> findNotification(@CookieValue String access) {
        // todo : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함 => O
//        Long id=1L;

        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long id = Long.parseLong(claims.get("memberId").toString());

        List<FindNotificationResponse> response=notificationService.findNotification("sksn12",id);
        return ResponseEntity.ok(response);
    }

    // 알림 생성
    // TODO : 헤더 토큰에 sendMemberId가져와서 사용 => O
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody List<CreateNotificationRequest> request, @CookieValue String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long id = Long.parseLong(claims.get("memberId").toString());

        notificationService.createNotification(request, id);
        return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<String> updateNotification(@RequestBody UpdateNotificationRequest request, @CookieValue String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long id = Long.parseLong(claims.get("memberId").toString());

        notificationService.updateNotification(request, id);
        return ResponseEntity.ok("ok");
    }

    private String returnAccessToken(String fullToken){
        String parseToken = "";
        if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
            parseToken = fullToken.substring(7);
        }
        return parseToken;
    }
}
