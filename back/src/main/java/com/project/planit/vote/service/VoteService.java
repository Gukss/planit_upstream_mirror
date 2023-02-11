package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.project.planit.vote.service
 * fileName       : VoteService
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
 *
 */
public interface VoteService {

    /**
     * methodName : createVote
     * author : Gukss
     * description : 방에 해당하는 투표 생성
     * Create vote vote.
     *
     * @param request the CreateVoteRequest
     * @return the vote; 생성된 투표
     */
    public Vote createVote(CreateVoteRequest request, Long memberId);


    /**
     * methodName : findByRoom
     * author : Gukss
     * description : 방에 해당하는 투표 조회
     * Find by room optional.
     *
     * @param room the room
     * @return the Optional<List<Vote>>; 방에 있는 투표 리스트
     */
    public List<Vote> findAllByRoom(Room room);


    /**
     * methodName : updateVote
     * author : Gukss
     * description : 해당하는 투표 제목 갱신
     * Update vote optional.
     *
     * @param request the UpdateVoteRequest
     * @return the Optional<vote>; 갱신한 투표
     */
    public Vote updateVote(UpdateVoteRequest request, Long memberId);

    public Vote findById(Long id);
}
