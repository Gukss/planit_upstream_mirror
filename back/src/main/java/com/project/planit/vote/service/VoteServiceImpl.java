package com.project.planit.vote.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * packageName    : com.project.planit.vote.service
 * fileName       : VoteService
 * author         : Gukss
 * date           : 2023-01-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR   NOTE
 * -----------------------------------------------------------
 * 2023-01-25        Gukss       최초생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

  private final RoomRepository roomRepository;

  private final VoteRepository voteRepository;

  //방에 해당하는 투표 생성
  @Override
  @Transactional
  public Vote createVote(@RequestBody CreateVoteRequest request){
    Long roomId = request.getRoomId();
    Room currentRoom = roomRepository.findById(roomId).get();
    //todo: 토큰에서 constructor, updater 값(memberAppId)을 받아와서 변경해주기
    String constructor = "Gukss";
    String updater = "Gukss";
    Vote vote = Vote.builder()
            .room(currentRoom)
            .title(request.getTitle())
            .baseRequest(BaseRequest.builder()
                .constructor(constructor)
                .updater(updater)
                .build())
            .build();
    return voteRepository.save(vote);
  }

  //방에 해당하는 투표 조회
  @Override
  public List<Vote> findAllByRoom(Room room) {
    return voteRepository.findAllByRoom(room).orElseThrow(()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
  }

  //해당하는 투표 제목 갱신
  @Override
  @Transactional
  public Vote updateVote(UpdateVoteRequest request) {
    Vote targetVote = voteRepository.findById(request.getVoteId()).orElseThrow(
        ()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
    targetVote.changeTitle(request.getNewTitle()); //jpa는 영속성 컨텍스트의 값을 바꾸기만 해도 update 쿼리 날려준다.
    return targetVote;
  }


  @Override
  public Vote findById(Long id) {
    return voteRepository.findById(id).orElseThrow(()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
  }

  //방에 해당하는 투표 갱신

}
