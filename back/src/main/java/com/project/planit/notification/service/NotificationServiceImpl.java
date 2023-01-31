package com.project.planit.notification.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import com.project.planit.notification.repository.NotificationRepository;
import java.util.HashMap;
import java.util.List;
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
    public HashMap<Long, FindNotificationResponse> findNotification(String memberAppId) {
        Member member = memberRepository.findByAppId(memberAppId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        List<Notification> notifications =notificationRepository.findAllBySendMemberId(member);

        HashMap<Long, FindNotificationResponse> notificationResponseHashMap=new HashMap<>();

        for (Notification notification:notifications) {
            notificationResponseHashMap.put(
                notification.getId(),
                FindNotificationResponse.builder()
                    .receivedMemberId(notification.getReceivedMemberId().getAppId())
                    .sendMemberId(notification.getSendMemberId().getAppId())
                    .createdAt(notification.getCreated_at())
                    .readOrNot(notification.isReadOrNot())
                    .build()
            );
        }

        return notificationResponseHashMap;
    }


    @Override
    @Transactional
    public boolean createNotification(CreateNotificationRequest request) {
        Member recevierMember=memberRepository.findByAppId(request.getReceiverMemberId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // 토큰에서 sendMemberId값을 받아오는데 이때 이 id로 find해서 Member객체를 가져와야함!!
//        Member snedMember=memberRepository.findByAppId(request.getReceiverMemberId())
//            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Notification notification=Notification.create(false, recevierMember);
        notificationRepository.save(notification);
        return false;
    }

    @Override
    @Transactional
    public boolean updateNotification(UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        notification.update(request);
        return false;
    }
}
