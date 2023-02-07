package com.project.planit.vote.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundVoteException;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.util.BaseRequest;
=======
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
<<<<<<< HEAD
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
=======
 * packageName    : com.project.planit.vote.service fileName       : VoteService author         :
 * Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-25        Gukss       최초
 * 생성
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
<<<<<<< HEAD
    Long roomId = request.getRoomId();
    Room currentRoom = roomRepository.findById(roomId).orElseThrow(()->new NotFoundVoteException(
        NotFoundExceptionMessage.ROOM_NOT_FOUND));
    //todo: tocken에서 memberAppId 가져다 사용하기
    String updater = "Gukss";
    String constructor = "Gukss";

    BaseRequest baseRequest = BaseRequest.builder()
        .updater(updater)
        .constructor(constructor)
        .build();

    String title = request.getTitle();

    Vote vote = Vote.create(title, currentRoom, baseRequest);
=======
    //room id를 받아와서 방을 조회
    Long roomId = request.getRoomId();
    Room currentRoom = roomRepository.findById(roomId).get();
    Vote vote = Vote.builder()
            .room(currentRoom)
            .title(request.getTitle())
            .baseRequest(request.getBaseRequest())
            .build();
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    return voteRepository.save(vote);
  }

  //방에 해당하는 투표 조회
  @Override
<<<<<<< HEAD
  public List<Vote> findAllByRoom(Room room) {
    return voteRepository.findAllByRoom(room).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.ROOM_NOT_FOUND));
=======
  public List<Vote> findByRoom(Room room) {
    return voteRepository.findAllByRoom(room).orElseThrow(()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  //해당하는 투표 제목 갱신
  @Override
  @Transactional
  public Vote updateVote(UpdateVoteRequest request) {
    Vote targetVote = voteRepository.findById(request.getVoteId()).orElseThrow(
<<<<<<< HEAD
        ()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));
=======
        ()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    targetVote.changeTitle(request.getNewTitle()); //jpa는 영속성 컨텍스트의 값을 바꾸기만 해도 update 쿼리 날려준다.
    return targetVote;
  }


  @Override
  public Vote findById(Long id) {
<<<<<<< HEAD
    return voteRepository.findById(id).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));
=======
    return voteRepository.findById(id).orElseThrow(()->new NotFoundException(NotFoundException.VOTE_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  //방에 해당하는 투표 갱신

}
