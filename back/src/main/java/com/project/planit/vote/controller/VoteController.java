package com.project.planit.vote.controller;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.dto.CreateVoteResponse;
import com.project.planit.vote.dto.FindVoteResponse;
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.UpdatevoteResponse;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteServiceImpl;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.controller
 * fileName       : VoteController
 * author         : Gukss
 * date           : 2023-01-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR   NOTE
 * -----------------------------------------------------------
 * 2023-01-25        Gukss       최초생성
 * 2023-01-31        Gukss       REST API 문서에 맞게 수정
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/votes")
public class VoteController {
  private final VoteServiceImpl voteService;
  private final RoomServiceImpl roomService;

  @PostMapping
  public ResponseEntity<CreateVoteResponse> createVote(@RequestBody CreateVoteRequest request){
//    log.info("vote controller");
    Vote createdVote = voteService.createVote(request);
    Long voteId = createdVote.getId();

    //service에서 받아온 vote의 roomId 조회해서 request로 받아온 roomId와 동일한지 검사해주기
    Long targetRoomId = createdVote.getRoom().getId();
    Long confirmRoomId = request.getRoomId();
    CreateVoteResponse createVoteResponse = CreateVoteResponse.create(voteId);

    ResponseEntity res = null;
    if(targetRoomId.equals(confirmRoomId)){ //정상
      res = ResponseEntity.ok().body(createVoteResponse);
    }else{ //오류
      res = ResponseEntity.badRequest().body(createVoteResponse);
    }

    return res;
  }

  @GetMapping(path = "{roomId}")
  public ResponseEntity<List<FindVoteResponse>> findVoteByRoomId(@PathVariable Long roomId){
    //fetch.lazy 때문에 room이 바로 초기화되지 않고 정보가 채워지는 프록시 객체로 채워진다.
    //room의 초기화가 안되어있기 때문에 room으로 voteList를 찾아올 수 없다.
    Room room = roomService.findById(roomId);
    List<Vote> foundVotes = voteService.findAllByRoom(room);

    List<FindVoteResponse> resList = new ArrayList<>();
    for(Vote vote: foundVotes){
      resList.add(vote.createFindVoteResponse());
    }

    //foundVotes의 첫 번째 roomId와 PathVariable의 roomId를 비교하면 검증할 수 있다.
    ResponseEntity res = null;
    //0번째꺼 가지고 오면 리스트가 비어있으면 nullPonint
    Long foundRoomId = -1L; //-1로 초기화
    if(!foundVotes.isEmpty()){ //리스트가 비어있지 않으면 값을 가지고 오고, 아니면 -1로 검사한다.
      foundRoomId = foundVotes.get(0).getRoom().getId();
    }

    //todo: foundRoomId가 -1일 때(리스트가 비어있을 때)와 foundRoomId와 roomId가 다를 때 두 가지를 분리해야하는가?
    if(foundRoomId.equals(roomId)){ //정상
      res = ResponseEntity.ok().body(resList);
    }else{ //오류
      res = ResponseEntity.badRequest().body(resList);
    }
    return res;
  }

  @PatchMapping
  public ResponseEntity<UpdatevoteResponse> updateVote(@RequestBody UpdateVoteRequest request) {
    Vote updatedVote = voteService.updateVote(request);

    ResponseEntity res = null;
    Long updatedVoteId = updatedVote.getId();
    Long requestVoteId = request.getVoteId();
    UpdatevoteResponse updatevoteResponse = UpdatevoteResponse.create(updatedVote.getId(),
        updatedVote.getTitle());

    if(updatedVoteId.equals(requestVoteId)){ //정상
      res = ResponseEntity.ok().body(updatevoteResponse);
    }else{ //오류
      res = ResponseEntity.badRequest().body(updatevoteResponse);
    }

    return res;
  }
}
