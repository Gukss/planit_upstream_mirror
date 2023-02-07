package com.project.planit.vote.controller;

<<<<<<< HEAD
=======
import com.project.planit.common.exception.NotFoundException;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.dto.CreateVoteResponse;
import com.project.planit.vote.dto.FindVoteResponse;
import com.project.planit.vote.dto.UpdateVoteRequest;
import com.project.planit.vote.dto.UpdatevoteResponse;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteServiceImpl;
<<<<<<< HEAD

import java.net.URI;
import java.util.ArrayList;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
<<<<<<< HEAD
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
=======
 * packageName    : com.project.planit.vote.controller fileName       : VoteController author
 *  : Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-25        Gukss       최초
 * 생성
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
<<<<<<< HEAD
    Long voteId = createdVote.getId();
    CreateVoteResponse createVoteResponse = CreateVoteResponse.create(voteId);
    URI uri = URI.create(""+createdVote.getId());
    ResponseEntity res = ResponseEntity.created(uri).body(createVoteResponse);
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
    ResponseEntity res = ResponseEntity.ok().body(resList);

    return res;
=======
    CreateVoteResponse createVoteResponse = CreateVoteResponse.create(createdVote);
    return ResponseEntity.ok()
//        .header()
        .body(createVoteResponse);
  }

  @GetMapping(path = "{roomId}")
  public ResponseEntity<FindVoteResponse> findVoteByRoomId(@PathVariable Long roomId){
    //fetch.lazy 때문에 room이 바로 초기화되지 않고 정보가 채워지는 프록시 객체로 채워진다.
    //room의 초기화가 안되어있기 때문에 room으로 voteList를 찾아올 수 없다.
    Room room = roomService.findById(roomId);
    List<Vote> foundVotes = voteService.findByRoom(room);

    return ResponseEntity.ok()
        .body(FindVoteResponse.create(foundVotes));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  @PatchMapping
  public ResponseEntity<UpdatevoteResponse> updateVote(@RequestBody UpdateVoteRequest request) {
    Vote updatedVote = voteService.updateVote(request);
<<<<<<< HEAD

    Long updatedVoteId = updatedVote.getId();
    Long requestVoteId = request.getVoteId();
    UpdatevoteResponse updatevoteResponse = UpdatevoteResponse.create(updatedVote.getId(),
        updatedVote.getTitle());
    ResponseEntity res = ResponseEntity.ok().body(updatevoteResponse);

    return res;
=======
    return ResponseEntity.ok()
            .body(UpdatevoteResponse.create(updatedVote.getId(),
                    updatedVote.getTitle())
            );
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }
}
