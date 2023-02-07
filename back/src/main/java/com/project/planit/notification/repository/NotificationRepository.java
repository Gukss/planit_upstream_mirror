package com.project.planit.notification.repository;

import com.project.planit.member.entity.Member;
<<<<<<< HEAD
import com.project.planit.memberRoom.entity.MemberRoom;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.notification.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
  List<Notification> findAllBySendMemberId (Member sendMemberId);
  Optional<Notification> findById(Long notificationId);
}
