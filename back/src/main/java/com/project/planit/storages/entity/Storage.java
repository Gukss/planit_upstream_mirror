package com.project.planit.storages.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.project.planit.storages.entity
 * fileName       : Storage
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
@Table(name="storage")
public class Storage {

    @Id @GeneratedValue
    @Column(name="storage_id")
    private Long id;

    @Column(name="storage_name")
    private String storageName;

    private boolean confirmed;

    @Column(name="day_order")
    private int dayOrder;

    private double lat;

    private double lng;

    @Column(name="category_name")
    @Enumerated(EnumType.STRING) //이넘 타입은 문자열로
    private Category categoryName;

    @Embedded
    private BaseEntity baseEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;
}
