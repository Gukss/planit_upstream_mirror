package com.project.planit.room.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.room.dto.UpdateRoomRequest;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.rooms.entity
 * fileName       : Room
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
@Table(name="room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Room extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;

    @Column(name="room_name")
    @NotNull
    private String roomName;

    @Column(name="start_date")
    @NotNull
    private LocalDate startDate;

    @Column(name="end_date")
    @NotNull
    private LocalDate endDate;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    public void changeName(String newName){
        //바꾼 이름이 비어있을 때 untitle로 변경
        if(newName.equals("")){
            newName = "untitle";
        }
        this.roomName = newName;
    }

    public void changeDate(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void update(UpdateRoomRequest request, Member member){
        this.roomName = request.getRoomName();
        this.endDate = request.getEndDate();
        this.startDate = request.getStartDate();

        this.baseRequest = BaseRequest.builder()
                .constructor(this.baseRequest.getConstructor())
                .updater(member.getAppId())
                .build();
    }

    public static Room create(String roomName, LocalDate startDate, LocalDate endDate, BaseRequest baseRequest){
        return Room.builder()
                .roomName(roomName)
                .baseRequest(baseRequest)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
