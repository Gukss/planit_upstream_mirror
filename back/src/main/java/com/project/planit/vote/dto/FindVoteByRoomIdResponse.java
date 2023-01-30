package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : FindVoteByRoomIdResponse
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
@Data

public class FindVoteByRoomIdResponse {
    List<Vote> foundVotes;

    public FindVoteByRoomIdResponse(List<Vote> foundVotes){this.foundVotes = foundVotes;};
}
