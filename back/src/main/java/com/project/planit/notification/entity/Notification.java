package com.project.planit.notification.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.util.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

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
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue
    @Column(name="notification_id", nullable = false) //알림번호
    private Long id;

    @Column(name="read_or_not")
    private boolean readOrNot;

    @Embedded
    private BaseEntity baseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id" ,referencedColumnName = "member_id",insertable = false, updatable = false)
    private Member receivedMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member sendMemberId;
}
