package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class VoteService {

  private final VoteRepository voteRepository;

  //방에 해당하는 투표 생성
  @Transactional
  public Long createVote(CreateVoteRequest request){
    Vote vote = Vote.builder()
        .room(request.getRoom())
        .baseEntity(request.getBaseEntity())
        .build();
    voteRepository.save(vote);
    return vote.getId();
  }

  //방에 해당하는 투표 조회
  public Optional<Vote> findVotes(Room room){
    return voteRepository.findByRoom(room);
  }

  //방에 해당하는 투표 갱신


  //투표 단건 조회
  public Vote findOne(Long id){
//    return voteRepository.findOne(id);
    return null;
  }
}
