package com.project.planit.notification.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.notification.dto.createNotificationRequest;
import com.project.planit.notification.dto.updateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import com.project.planit.notification.repository.NotificationRepository;
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
    public boolean findNotification(String memberAppId) {

        return false;
    }


    @Override
    @Transactional
    public boolean createNotification(createNotificationRequest request) {
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
    public boolean updateNotification(updateNotificationRequest request) {
        return false;
    }
}
