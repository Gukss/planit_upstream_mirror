package com.project.planit.memberRoom.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseRequest;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.memberRoom.entity
 * fileName       : MemberRoom
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
@Table(name = "member_room")
public class MemberRoom {

    @Id
    @Column(name="member_room_id")
    @GeneratedValue
    private Long id;

    private boolean participation;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Member member;
}
