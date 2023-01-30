package com.project.planit.vote.dto;

import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : ChangeTitleRequest
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
public class UpdateVoteRequest {
    private Long voteId;

    private Long roomId;

    private String newTitle;

    @Embedded
    private BaseRequest baseRequest;
}
