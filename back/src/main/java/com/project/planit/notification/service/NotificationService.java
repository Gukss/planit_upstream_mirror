package com.project.planit.notification.service;

import com.project.planit.notification.dto.createNotificationRequest;
import com.project.planit.notification.dto.updateNotificationRequest;

public interface NotificationService {
    boolean findNotification(String memberAppId);
    boolean createNotification(createNotificationRequest request);
    boolean updateNotification(updateNotificationRequest request);
}
