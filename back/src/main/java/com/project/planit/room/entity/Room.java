package com.project.planit.rooms.entity;

import com.project.planit.util.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Room {
    @Id
    @GeneratedValue
    @Column(name="room_id")
    private Long id;

    private String room_name;

    private LocalDate start_date;

    private LocalDate end_date;

    private BaseEntity baseEntity;


}
