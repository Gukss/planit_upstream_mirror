package com.project.planit.voteItemMember.entity;

import com.project.planit.member.entity.Member;
import com.project.planit.util.BaseEntity;
import com.project.planit.util.BaseRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItemMember.dto.FindVoteItemMemberResponse;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteItemMember extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_item_member_id")
    private Long id;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vote_item_id")
    private VoteItem voteItem;

//    //==생성 메서드==
    public static VoteItemMember create(BaseRequest baseRequest, Member member, VoteItem voteItem){
        return VoteItemMember.builder()
                .member(member)
                .voteItem(voteItem)
                .baseRequest(baseRequest)
                .build();
    }

    public FindVoteItemMemberResponse createFindVoteItemMemberResponse(){
        return FindVoteItemMemberResponse.builder()
            .voteItemId(this.voteItem.getId())
            .memberId(this.getMember().getId())
            .build();
    }

}
