package com.project.planit.vote.controller;

import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.dto.CreateVoteResponse;
import com.project.planit.vote.dto.FindVoteByRoomIdResponse;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.controller fileName       : VoteController author
 *  : Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-25        Gukss       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/votes")
public class VoteController {
  private final RoomRepository roomRepository;
  private final VoteServiceImpl voteService;
  private final RoomServiceImpl roomService;

  @PostMapping
  public ResponseEntity<CreateVoteResponse> createVote(@RequestBody CreateVoteRequest request){
//    log.info("vote controller");
    Vote createdVote = voteService.createVote(request);
    CreateVoteResponse createVoteResponse = CreateVoteResponse.create(createdVote);
    return ResponseEntity.ok()
//        .header()
        .body(createVoteResponse);
  }

  @GetMapping
  public FindVoteByRoomIdResponse findVoteByRoomId(@PathVariable Long roomId){
    Room room = roomRepository.findById(roomId).get();
    List<Vote> foundVotes = voteService.findByRoom(room).get();
    return new FindVoteByRoomIdResponse(foundVotes);
  }
}
