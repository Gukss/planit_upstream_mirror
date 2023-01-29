package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.project.planit.vote.service
 * fileName       : VoteService
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
public interface VoteService {
    //방에 해당하는 투표 생성
    public Vote createVote(CreateVoteRequest request);

    //방에 해당하는 투표 조회
    public Optional<List<Vote>> findByRoom(Room room);

    //투표 단건 조회
    public Optional<Vote> findOne(Long id);
}
