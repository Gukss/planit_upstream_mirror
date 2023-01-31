package com.project.planit.chatting.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;

import com.project.planit.util.BaseRequest;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.chatting.entity
 * fileName       : ChattingMessage
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
@Table(name="chatting_message")
public class ChattingMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatting_id")
    private Long id;

    private String message;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;
}
