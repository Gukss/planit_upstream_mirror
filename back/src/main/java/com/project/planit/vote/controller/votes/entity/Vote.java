package com.project.planit.vote.controller.votes.entity;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.project.planit.votes.entity
 * fileName       : Vote
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
public class Vote {

    @Id @GeneratedValue
    @Column(name = "vote_id")
    private Long id;

    private String title;

    @Embedded
    private BaseEntity baseEntity;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;
}
