package com.project.planit.voteItemMember.dto;

import com.project.planit.member.entity.Member;
import com.project.planit.util.BaseRequest;
import com.project.planit.voteItem.entity.VoteItem;
import lombok.*;

import javax.persistence.Embedded;

/**
 * packageName    : com.project.planit.voteItemMember.dto
 * fileName       : CreateVoteItemMemberRequest
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteItemMemberRequest {

    @Embedded
    private BaseRequest baseRequest;

    private Long memberId;

    private Long voteItemId;

    public static CreateVoteItemMemberRequest create(Long memberId, Long voteItemId, BaseRequest baseRequest){
        return CreateVoteItemMemberRequest.builder()
                .memberId(memberId)
                .voteItemId(voteItemId)
                .baseRequest(baseRequest)
                .build();
    }
}
