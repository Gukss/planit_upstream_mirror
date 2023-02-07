package com.project.planit.notification.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.memberRoom.entity.MemberRoom;
import com.project.planit.memberRoom.repository.MemberRoomRepository;
=======
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import com.project.planit.notification.repository.NotificationRepository;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;


import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
=======
import java.util.HashMap;
import java.util.List;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
<<<<<<< HEAD
    private final MemberRoomRepository memberRoomRepository;
    
    private final RoomRepository roomRepository;
    @Override
    public List<FindNotificationResponse> findNotification(String memberAppId,Long id) {
        Member member = memberRepository.findByAppId(memberAppId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        List<Notification> notifications =notificationRepository.findAllBySendMemberId(member);

        List<MemberRoom> memberRoom = memberRoomRepository.findAllByMember(member);

        List<FindNotificationResponse> response=new ArrayList<>();


        // 알림과 회원_방 갯수를 알 수 없음으로 2중 for문을 돌아야함
        for (MemberRoom memberRoomItem:memberRoom){
            System.out.println(memberRoomItem.getId());
            System.out.println("12312312");
            for (Notification NotificationItem:notifications){
                System.out.println(NotificationItem.getId());
                System.out.println("여기여기");
                if(memberRoomItem.getMember().getId().equals(NotificationItem.getId())){
                    System.out.println("들어가나요>");
                    Room room=roomRepository.findById(memberRoomItem.getRoom().getId())
                            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.ROOM_NOT_FOUND));

                    response.add(FindNotificationResponse.builder()
                            .roomName(room.getRoomName())
                            .read(NotificationItem.getReadOrNot())
                            .sendMemberName(NotificationItem.getSendMemberId().getName())
                            .createdAt(NotificationItem.getCreated_at())
                            .build());
                }
            }

        }


        return response;
=======
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
                    .readOrNot(notification.getReadOrNot())
                    .build()
            );
        }

        return notificationResponseHashMap;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }


    @Override
    @Transactional
<<<<<<< HEAD
    public void createNotification(List<CreateNotificationRequest> request) {

        for (CreateNotificationRequest requestItem:request){
            Member recevierMember=memberRepository.findByAppId(requestItem.getReceiverMemberId())
                    .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

            // 토큰에서 sendMemberId값을 받아오는데 이때 이 id로 find해서 Member객체를 가져와야함!!
            Member snedMember=memberRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

            Notification notification=Notification.create(false, recevierMember,snedMember);

            notificationRepository.save(notification);
        }

=======
    public boolean createNotification(CreateNotificationRequest request) {
        Member recevierMember=memberRepository.findByAppId(request.getReceiverMemberId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // 토큰에서 sendMemberId값을 받아오는데 이때 이 id로 find해서 Member객체를 가져와야함!!
        Member snedMember=memberRepository.findById(1L)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Notification notification=Notification.create(false, recevierMember,snedMember);
        notificationRepository.save(notification);
        return false;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public void updateNotification(UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.NOTIFICATION_NOT_FOUND));

        notification.update(request);
=======
    public boolean updateNotification(UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        notification.update(request);
        return false;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }
}
