package com.project.planit.voteItem.dto;

import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import lombok.*;

import javax.persistence.Embedded;

/**
 * packageName    : com.project.planit.voteItem.dto
 * fileName       : CreateVoteItemRequest
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CreateVoteItemRequest {

    @Embedded
    private BaseRequest baseRequest;

    private String voteItemName;
    private Vote vote;
}
