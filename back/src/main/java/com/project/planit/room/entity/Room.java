package com.project.planit.room.entity;

import com.project.planit.util.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

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
public class Room {
    @Id
    @GeneratedValue
    @Column(name="room_id")
    private Long id;

    @Column(name="room_name")
    private String roomName;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    private BaseEntity baseEntity;
}
