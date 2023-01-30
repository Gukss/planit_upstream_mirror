package com.project.planit.notification.service;

import com.project.planit.notification.dto.createNotificationRequest;
import com.project.planit.notification.dto.findNotificationResponse;
import com.project.planit.notification.dto.updateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import java.util.HashMap;
import java.util.List;

public interface NotificationService {
    HashMap<Long, findNotificationResponse> findNotification(String memberAppId);
    boolean createNotification(createNotificationRequest request);
    boolean updateNotification(updateNotificationRequest request);
}
