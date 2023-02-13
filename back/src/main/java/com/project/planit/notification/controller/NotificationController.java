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
    public SseEmitter subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,@RequestHeader("Authorization") String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long memberId = Long.parseLong(claims.get("memberId").toString());

        return notificationService.subscribe(memberId,lastEventId);
    }


    @GetMapping
    public ResponseEntity<List<FindNotificationResponse>> findNotification(@RequestHeader("Authorization") String access) {
        // todo : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함 => O
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long memberId = Long.parseLong(claims.get("memberId").toString());
        String memberAppId = claims.get("memberAppId").toString();
        List<FindNotificationResponse> response=notificationService.findNotification(memberAppId,memberId);
        return ResponseEntity.ok(response);
    }

    // 알림 생성
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody List<CreateNotificationRequest> request, @RequestHeader("Authorization") String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        System.out.println("여까진 되자나!");
        Long id = Long.parseLong(claims.get("memberId").toString());
        System.out.println(id);
        notificationService.createNotification(request, id);

        for (CreateNotificationRequest notificationItem:request){
            System.out.println("여기여기요기여기되;나요?!!!!!!!!!");
            String inviteMessage=notificationItem.getReceiverMemberId()+"님이 초대 하셨습니다";
            // value header에서 받아오는 id값으로 변경
            notificationService.send(id, inviteMessage,"message");
        }

        return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<String> updateNotification(@RequestBody UpdateNotificationRequest request, @RequestHeader("Authorization") String access) {
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
