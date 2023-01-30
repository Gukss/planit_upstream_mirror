package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
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
public class FindVoteByRoomIdResponse {
    List<Vote> foundVotes;

    public static FindVoteByRoomIdResponse create(List<Vote> foundVotes){
        return FindVoteByRoomIdResponse.builder()
            .foundVotes(foundVotes)
            .build();
    }
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
}
