package com.project.planit.voteItem.dto;

import com.project.planit.vote.entity.Vote;
import com.project.planit.voteItem.entity.VoteItem;
import lombok.*;

import java.util.List;

/**
 * packageName    : com.project.planit.voteItem.dto
 * fileName       : FindVoteItemListResponse
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class FindVoteItemListResponse {
    List<VoteItem> foundVoteItems;

    public static FindVoteItemListResponse create(List<VoteItem> foundVoteItems){
        return FindVoteItemListResponse.builder()
                .foundVoteItems(foundVoteItems)
                .build();
    }
}
