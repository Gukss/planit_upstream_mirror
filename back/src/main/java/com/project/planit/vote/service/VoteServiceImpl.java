package com.project.planit.vote.service;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.ChangeTitleRequest;
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
            .baseRequest(request.getBaseRequest())
            .build();
    Vote newVote = voteRepository.save(vote);
    return newVote;
  }

  //방에 해당하는 투표 조회
  @Override
  public Optional<List<Vote>> findByRoom(Room room) {
    return voteRepository.findByRoom(room);
  }

  //해당하는 투표 제목 갱신
  @Override
  @Transactional
  public Optional<Vote> changeTitle(ChangeTitleRequest request) {
    Vote targetVote = voteRepository.findById(request.getVoteId()).get();
    targetVote.changeTitle(request.getNewTitle()); //jpa는 영속성 컨텍스트의 값을 바꾸기만 해도 update 쿼리 날려준다.
    return Optional.of(targetVote);
  }

  //방에 해당하는 투표 갱신

}
