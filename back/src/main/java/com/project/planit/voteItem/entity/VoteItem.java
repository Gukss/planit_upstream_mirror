package com.project.planit.voteItem.entity;

import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.voteItem.entity
 * fileName       : VoteItem
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
@Table(name="vote_item")
public class VoteItem {

    @Id
    @Column(name="vote_item_id")
    @GeneratedValue
    private Long id;

    @Column(name="vote_item_name")
    private  String voteItemName;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vote_id")
    private Vote vote;
}
