package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : FindVoteByRoomIdResponse
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FindVoteResponse {
<<<<<<< HEAD
        private Long voteId;
        private String title;
=======
    List<Vote> foundVotes;

    public static FindVoteResponse create(List<Vote> foundVotes) {
        return FindVoteResponse.builder()
                .foundVotes(foundVotes)
                .build();
    }
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
