package com.project.planit.notification.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.notification.dto.UpdateNotificationRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.notification.entity
 * fileName       : Notification
 * author         : dongk
 * date           : 2023-01-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-23        dongk       최초 생성
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="notification")
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id", nullable = false) //알림번호
    private Long id;

    @Column(name="read_or_not")
    private Boolean readOrNot;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "received_member_id",referencedColumnName = "member_id")
    private Member receivedMemberId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "send_member_id",referencedColumnName = "member_id")
    private Member sendMemberId;

    // sendMemberId를 토큰에서 받아와서 생성
    public static Notification create(boolean read,Member receiverMember,Member sendMemberId){
        Notification notification=Notification.builder()
            .readOrNot(read)
            .receivedMemberId(receiverMember)
            .sendMemberId(sendMemberId) // 나중에 sendMember로 변경
            .baseRequest(BaseRequest.builder()
                .constructor(receiverMember.getAppId())
                .updater(receiverMember.getAppId())
                .build())
            .build();
        return notification;
    }

    public void update(UpdateNotificationRequest request, Member member){
        this.readOrNot=request.getRead();
        this.baseRequest = BaseRequest.builder()
                .constructor(this.baseRequest.getConstructor())
                .updater(member.getAppId())
                .build();
    }
}
