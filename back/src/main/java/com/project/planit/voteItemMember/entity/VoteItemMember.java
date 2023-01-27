package com.project.planit.voteItemMember.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.util.BaseEntity;
import com.project.planit.voteItem.entity.VoteItem;
import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.project.planit.VoteItemMember.entity
 * fileName       : VoteItemMember
<<<<<<< HEAD
 * author         : Gukss
=======
 * author         : dongk
>>>>>>> 237e86f6acefaf78e84fbb87d0918dfa1458639d
 * date           : 2023-01-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
<<<<<<< HEAD
 * 2023-01-23        Gukss       최초 생성
=======
 * 2023-01-23        dongk       최초 생성
>>>>>>> 237e86f6acefaf78e84fbb87d0918dfa1458639d
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
