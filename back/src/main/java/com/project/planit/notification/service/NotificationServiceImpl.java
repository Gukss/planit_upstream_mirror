package com.project.planit.notification.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.notification.dto.createNotificationRequest;
import com.project.planit.notification.dto.findNotificationResponse;
import com.project.planit.notification.dto.updateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import com.project.planit.notification.repository.NotificationRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    @Override
    public HashMap<Long,findNotificationResponse> findNotification(String memberAppId) {
        Member member = memberRepository.findByAppId(memberAppId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        List<Notification> notifications =notificationRepository.findAllBySendMemberId(member);

        HashMap<Long,findNotificationResponse> notificationResponseHashMap=new HashMap<>();

        for (Notification notification:notifications) {
            notificationResponseHashMap.put(
                notification.getId(),
                findNotificationResponse.builder()
                    .receivedMemberId(notification.getReceivedMemberId().getAppId())
                    .sendMemberId(notification.getSendMemberId().getAppId())
                    .createdAt(notification.getCreated_at())
                    .readOrNot(notification.getReadOrNot())
                    .build()
            );
        }

        return notificationResponseHashMap;
    }


    @Override
    @Transactional
    public boolean createNotification(createNotificationRequest request) {
        Member recevierMember=memberRepository.findByAppId(request.getReceiverMemberId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // 토큰에서 sendMemberId값을 받아오는데 이때 이 id로 find해서 Member객체를 가져와야함!!
        Member snedMember=memberRepository.findById(1L)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Notification notification=Notification.create(false, recevierMember,snedMember);
        notificationRepository.save(notification);
        return false;
    }

    @Override
    @Transactional
    public boolean updateNotification(updateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        notification.update(request);
        return false;
    }
}
