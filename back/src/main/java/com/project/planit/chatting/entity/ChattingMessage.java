package com.project.planit.chatting.entity;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.dto.FindChattingMessageResponse;
import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;

import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.*;

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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="chatting_message")
public class ChattingMessage extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatting_id")
    private Long id;

    @NotNull
    private String message;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="member_id" ,referencedColumnName = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="room_id",referencedColumnName = "room_id")
    private Room room;

    public static ChattingMessage create(CreateChattingRequest request,Room room,Member member){
        ChattingMessage chattingMessage=ChattingMessage.builder()
                .message(request.getMessage())
                .room(room)
                .member(member)
                .baseRequest(BaseRequest.builder()
                        .updater(member.getAppId())
                        .constructor(member.getAppId())
                        .build())
                .build();

        return chattingMessage;
    }

    public FindChattingMessageResponse createFindChattingMessageResponse(){
        return FindChattingMessageResponse.builder()
                .message(this.message)
                .constructor(this.member.getAppId())
                .createdAt(this.getCreated_at())
                .build();
    }
}
