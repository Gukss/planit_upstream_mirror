package com.project.planit.notification.service;

import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface NotificationService {
    List<FindNotificationResponse> findNotification(String memberAppId, Long id);

    void createNotification(List<CreateNotificationRequest> request, Long id);
    void updateNotification(UpdateNotificationRequest request, Long id);


    SseEmitter subscribe(Long memberId, String lastEventId);
    void sendToClient(SseEmitter emitter, String id, Object data, String target);
    void send(Long userId, String value, String target);
}
