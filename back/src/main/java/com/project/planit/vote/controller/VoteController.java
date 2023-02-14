package com.project.planit.vote.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.storage.dto.SocketStorageRequest;
import com.project.planit.vote.dto.*;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteServiceImpl;

import java.net.URI;
import java.util.ArrayList;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.util.StringUtils;
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
  private final JwtProvider jwtProvider;
  private final SimpMessageSendingOperations messagingTemplate;


  @PostMapping
  public ResponseEntity<CreateVoteResponse> createVote(@RequestBody CreateVoteRequest request, @RequestHeader("Authorization") String access) {
//    log.info("vote controller");

    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long memberId = Long.parseLong(claims.get("memberId").toString());

    Vote createdVote = voteService.createVote(request, memberId);
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
  }

  @PatchMapping
  public ResponseEntity<UpdatevoteResponse> updateVote(@RequestBody UpdateVoteRequest request, @RequestHeader("Authorization") String access) {
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long id = Long.parseLong(claims.get("memberId").toString());

    Vote updatedVote = voteService.updateVote(request, id);

    Long updatedVoteId = updatedVote.getId();
    Long requestVoteId = request.getVoteId();
    UpdatevoteResponse updatevoteResponse = UpdatevoteResponse.create(updatedVote.getId(),
        updatedVote.getTitle());
    ResponseEntity res = ResponseEntity.ok().body(updatevoteResponse);

    return res;
  }

  private String returnAccessToken(String fullToken){
    String parseToken = "";
    if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
      parseToken = fullToken.substring(7);
    }
    return parseToken;
  }

  // pub, sub관리 컨트롤러 RequestMapping 무시..
  @MessageMapping("/votes")
  public void message(SocketVotesRequest socketVotesRequest){
    messagingTemplate.convertAndSend("/sub/votes/" + socketVotesRequest.getRoomId(), socketVotesRequest);
  }
}
