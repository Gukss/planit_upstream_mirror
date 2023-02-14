package com.project.planit.vote.service;

import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundVoteException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;

import java.util.List;
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
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정: baseRequest 생성 값 변경
 * 2023-02-03        Gukss       REST API 문서에 맞게 수정
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

  private final RoomRepository roomRepository;

  private final VoteRepository voteRepository;
  private final MemberRepository memberRepository;


  //방에 해당하는 투표 생성
  @Override
  @Transactional
  public Vote createVote(@RequestBody CreateVoteRequest request, Long memberId){
    Long roomId = request.getRoomId();
    Room currentRoom = roomRepository.findById(roomId).orElseThrow(()->new NotFoundVoteException(
        NotFoundExceptionMessage.ROOM_NOT_FOUND));
    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

    String updater = member.getAppId();
    String constructor = member.getAppId();

    BaseRequest baseRequest = BaseRequest.builder()
        .updater(updater)
        .constructor(constructor)
        .build();

    String title = request.getTitle();

    Vote vote = Vote.create(title, currentRoom, baseRequest);
    return voteRepository.save(vote);
  }

  //방에 해당하는 투표 조회
  @Override
  public List<Vote> findAllByRoom(Room room) {
    return voteRepository.findAllByRoom(room).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.ROOM_NOT_FOUND));
  }

  //해당하는 투표 제목 갱신
  @Override
  @Transactional
  public Vote updateVote(UpdateVoteRequest request, Long memberId) {
    Vote targetVote = voteRepository.findById(request.getVoteId()).orElseThrow(
        ()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));
    Member member=memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.ROOM_NOT_FOUND));
    targetVote.update(request, member); //jpa는 영속성 컨텍스트의 값을 바꾸기만 해도 update 쿼리 날려준다.
    return targetVote;
  }


  @Override
  public Vote findById(Long id) {
    return voteRepository.findById(id).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));
  }

  //방에 해당하는 투표 갱신

}
