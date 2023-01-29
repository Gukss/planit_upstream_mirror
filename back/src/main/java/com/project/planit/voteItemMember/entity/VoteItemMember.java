package com.project.planit.voteItemMember.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.util.BaseEntity;
import com.project.planit.voteItem.entity.VoteItem;
import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.project.planit.VoteItemMember.entity
 * fileName       : VoteItemMember
 * author         : Gukss
 * date           : 2023-01-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-23        Gukss       최초 생성
 */
@Entity
@Getter
@Table(name="vote_item_member")
public class VoteItemMember {

    @Id @GeneratedValue
    @Column(name="vote_item_member_id")
    private Long id;

    @Embedded
    private BaseEntity baseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vote_item_id")
    private VoteItem voteItem;
//
//    //==생성 메서드==
//    public static void

}
