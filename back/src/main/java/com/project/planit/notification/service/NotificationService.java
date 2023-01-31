package com.project.planit.notification.service;

import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import java.util.HashMap;

public interface NotificationService {
    HashMap<Long, FindNotificationResponse> findNotification(String memberAppId);
    boolean createNotification(CreateNotificationRequest request);
    boolean updateNotification(UpdateNotificationRequest request);
}
