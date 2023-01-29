package com.project.planit.voteItem.entity;

import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import javax.validation.constraints.NotBlank;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.voteItem.entity
 * fileName       : VoteItem
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
@Table(name="vote_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class VoteItem extends BaseEntity{

    @Id
    @Column(name="vote_item_id")
    @GeneratedValue
//    @NotNull
    private Long id;

    @Column(name="vote_item_name")
    @NotNull
    private  String voteItemName;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="vote_id")
    @NotNull
    private Vote vote;
}
