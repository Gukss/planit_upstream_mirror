package com.project.planit.memberRoom.entity;

import com.project.planit.member.dto.UpdateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.memberRoom.dto.CreateMemberRoomRequest;
import com.project.planit.memberRoom.dto.FindMemberRoomByRoomIdResponse;
import com.project.planit.memberRoom.dto.FindMemberRoomResponse;
import com.project.planit.memberRoom.dto.UpdateMemberRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_room")
public class MemberRoom extends BaseEntity {

    @Id
    @Column(name="member_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean participation;

    @NotNull
    private String colorCode;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;

    public static MemberRoom create(CreateMemberRoomRequest request,Room room,Member member) {
        MemberRoom memberRoom = MemberRoom.builder()
                .participation(true)
                .room(room)
                .member(member)
                .colorCode(request.getColorCode())
                .baseRequest(BaseRequest.builder()
                        .constructor(member.getAppId())
                        .updater(member.getAppId())
                        .build())
                .build();

        return memberRoom;
    }

    public void update(UpdateMemberRoomRequest request, Member member){
        this.participation=request.getParticipation();
        this.baseRequest = BaseRequest.builder()
                .constructor(this.baseRequest.getConstructor())
                .updater(member.getAppId())
                .build();
    }

    public FindMemberRoomResponse createFindMemberRoomResponse(){
        return FindMemberRoomResponse.builder()
                .roomId(this.room.getId())
                .memberId(this.member.getId())
                .participation(this.participation)
                .colorCode(this.colorCode)
                .build();
    }

    public FindMemberRoomByRoomIdResponse createFindMemberRoomByRoomIdResponse(){
        return FindMemberRoomByRoomIdResponse.builder()
            .memberName(this.member.getName())
            .memberId(this.member.getId())
            .build();
    }
}
