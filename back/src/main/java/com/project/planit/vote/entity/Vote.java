package com.project.planit.vote.entity;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseRequest;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.vote.entity
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
@Table(name="vote")
public class Vote {

    @Id @GeneratedValue
    @Column(name="vote_id")
    private Long id;

    private String title;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
