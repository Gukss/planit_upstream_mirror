package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
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
 * packageName    : com.project.planit.vote.service fileName       : VoteService author         :
 * Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-25        Gukss       최초
 * 생성
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
    //room id를 받아와서 방을 조회
    Long roomId = request.getRoomId();
    Room currentRoom = roomRepository.findById(roomId).get();
    Vote vote = Vote.builder()
            .room(currentRoom)
            .title(request.getTitle())
            .baseRequest(request.getBaseRequest())
            .build();
    return voteRepository.save(vote);
  }

  //방에 해당하는 투표 조회
  @Override
  public Optional<List<Vote>> findByRoom(Room room) {
    return voteRepository.findByRoom(room);
  }

  //해당하는 투표 제목 갱신
  @Override
  @Transactional
  public Optional<Vote> updateVote(UpdateVoteRequest request) {
    Vote targetVote = voteRepository.findById(request.getVoteId()).get();
    targetVote.changeTitle(request.getNewTitle()); //jpa는 영속성 컨텍스트의 값을 바꾸기만 해도 update 쿼리 날려준다.
    return Optional.of(targetVote);
  }

  @Override
  public Optional<Vote> findById(Long id) {
    return voteRepository.findById(id);
  }

  //방에 해당하는 투표 갱신

}
