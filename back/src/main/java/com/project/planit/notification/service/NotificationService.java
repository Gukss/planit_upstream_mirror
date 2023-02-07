package com.project.planit.notification.service;

import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

public interface NotificationService {
    List<FindNotificationResponse> findNotification(String memberAppId, Long id);
    void createNotification(List<CreateNotificationRequest> request);
    void updateNotification(UpdateNotificationRequest request);
=======
import java.util.HashMap;

public interface NotificationService {
    HashMap<Long, FindNotificationResponse> findNotification(String memberAppId);
    boolean createNotification(CreateNotificationRequest request);
    boolean updateNotification(UpdateNotificationRequest request);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
