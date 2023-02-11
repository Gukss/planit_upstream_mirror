package com.project.planit.notification.service;

import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.memberRoom.entity.MemberRoom;
import com.project.planit.memberRoom.repository.MemberRoomRepository;
import com.project.planit.notification.dto.CreateNotificationRequest;
import com.project.planit.notification.dto.FindNotificationResponse;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.notification.entity.Notification;
import com.project.planit.notification.repository.EmitterRepository;
import com.project.planit.notification.repository.NotificationRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    private final MemberRoomRepository memberRoomRepository;
    
    private final RoomRepository roomRepository;

    private final EmitterRepository emitterRepository;

    @Override
    public List<FindNotificationResponse> findNotification(String memberAppId,Long id) {
        Member member = memberRepository.findByAppId(memberAppId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        List<Notification> notifications =notificationRepository.findAllBySendMemberId(member);

        List<MemberRoom> memberRoom = memberRoomRepository.findAllByMember(member);

        List<FindNotificationResponse> response=new ArrayList<>();


        // 알림과 회원_방 갯수를 알 수 없음으로 2중 for문을 돌아야함
        for (MemberRoom memberRoomItem:memberRoom){
            for (Notification NotificationItem:notifications){
                System.out.println(memberRoomItem.getMember().getId());
                System.out.println(NotificationItem.getReceivedMemberId().getId());
                System.out.println("여기여기");
                if(memberRoomItem.getMember().getId().equals(NotificationItem.getReceivedMemberId().getId())){
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
    }


    @Override
    @Transactional
    public void createNotification(List<CreateNotificationRequest> request, Long sendMemberId) {

        for (CreateNotificationRequest requestItem:request){
            Member recevierMember=memberRepository.findByAppId(requestItem.getReceiverMemberId())
                    .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

            // 토큰에서 sendMemberId값을 받아오는데 이때 이 id로 find해서 Member객체를 가져와야함!!
            Member snedMember=memberRepository.findById(sendMemberId)
                    .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

            Notification notification=Notification.create(false, recevierMember,snedMember);

            notificationRepository.save(notification);
        }

    }

    @Override
    @Transactional
    public void updateNotification(UpdateNotificationRequest request, Long memberId) {
        Notification notification = notificationRepository.findById(request.getNotificationId())
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.NOTIFICATION_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        notification.update(request, member);
    }

    @Override
    @Transactional
    public SseEmitter subscribe(Long memberId, String lastEventId) {

        String id = memberId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(30*60 * 1000L));
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        // 503 에러를 방지하기 위한 더미"" 이벤트 전송
        // target값과 프론트에서 설정한 addEvent리스너의 값으로 어떤 이벤트를 발생시킬지 결정
        sendToClient(emitter, id, "EventStream Created. [userId=" + memberId + "]", "sse");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            System.out.println("미수신한 이벤트가 있습니다");
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(memberId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue(), "message"));
        }

        return emitter;
    }

    @Override
    @Transactional
    public void sendToClient(SseEmitter emitter, String id, Object data, String target) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name(target)
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
//            throw new RuntimeException("연결 오류!");
        }
    }

    @Override
    @Transactional
    public void send(Long userId, String value, String target) {
        String id = String.valueOf(userId);
        System.out.println(id);
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartWithById(id);
        System.out.println(sseEmitters);
        sseEmitters.forEach(
                (key, emitter) -> {
                    System.out.println(value);
                    emitterRepository.saveEventCache(key, value);
                    sendToClient(emitter, key, value, target);
                }
        );
    }
}
