package com.project.planit.notification.repository;

import com.project.planit.notification.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
  Optional<List<Notification>> findAllBySendMemberId (String sendMemberId);
}
