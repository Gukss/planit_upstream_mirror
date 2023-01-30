package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import lombok.Builder;
=======
>>>>>>> upstream/BE_feature
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : FindVoteByRoomIdResponse
<<<<<<< HEAD
 * author         : Gukss
=======
 * author         : dongk
>>>>>>> upstream/BE_feature
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
<<<<<<< HEAD
 * 2023-01-29        Gukss       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FindVoteByRoomIdResponse {
    List<Vote> foundVotes;

    public static FindVoteByRoomIdResponse create(List<Vote> foundVotes){
        return FindVoteByRoomIdResponse.builder()
            .foundVotes(foundVotes)
            .build();
    }
=======
 * 2023-01-29        dongk       최초 생성
 */
@Data

public class FindVoteByRoomIdResponse {
    List<Vote> foundVotes;

    public FindVoteByRoomIdResponse(List<Vote> foundVotes){this.foundVotes = foundVotes;};
>>>>>>> upstream/BE_feature
}
