package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

  private final VoteRepository voteRepository;

  //방에 해당하는 투표 생성
  @Transactional
  public Vote createVote(@RequestBody CreateVoteRequest request){
    Vote vote = Vote.builder()
            .room(request.getRoom())
            .title(request.getTitle())
            .build();
    Vote newVote = voteRepository.save(vote);
    return newVote;
  }

  //방에 해당하는 투표 조회
  @Override
  public Optional<List<Vote>> findByRoom(Room room) {
    return voteRepository.findByRoom(room);
  }

  //방에 해당하는 투표 갱신


  //투표 단건 조회
  public Optional<Vote> findOne(Long id){
    return voteRepository.findById(id);
  }
}
