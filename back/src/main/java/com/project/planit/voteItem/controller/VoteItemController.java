package com.project.planit.voteItem.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.room.entity.Room;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteServiceImpl;
import com.project.planit.voteItem.dto.*;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.service.VoteItemServiceImpl;
import java.net.URI;
import java.util.ArrayList;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.project.planit.voteItemMember.controller fileName       : VoteItemController
 * author         : Gukss date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        Gukss       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/votes/vote-item")
public class VoteItemController {

  private final VoteItemServiceImpl voteItemService;
  private final VoteServiceImpl voteService;
  private final JwtProvider jwtProvider;
  @PostMapping
  public ResponseEntity<CreateVoteItemResponse> createVoteItem(@RequestBody CreateVoteItemRequest request, @CookieValue String access) {
    //todo: token에서 updator 가져오기
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long memberId = Long.parseLong(claims.get("memberId").toString());

    VoteItem newVoteItem = voteItemService.createVoteItem(request, memberId);
    CreateVoteItemResponse createVoteItemResponse = CreateVoteItemResponse.create(
        newVoteItem.getId(), newVoteItem.getVoteItemName());
    URI uri = URI.create(""+newVoteItem.getId());
    return ResponseEntity.created(uri)
        .body(createVoteItemResponse);
  }

  @GetMapping(path = "{voteId}")
  public ResponseEntity<List<FindVoteItemResponse>> findVoteItemByVote(@PathVariable Long voteId){
    Vote foundVote = voteService.findById(voteId);
    List<VoteItem> foundVoteItems = voteItemService.findAllByVote(foundVote);

    List<FindVoteItemResponse> resList = new ArrayList<>();

    for(VoteItem voteItem: foundVoteItems){
      resList.add(voteItem.createFindVoteItemResponse());
    }

    return ResponseEntity.ok().body(resList);
  }

  @PatchMapping
  public ResponseEntity<UpdateVoteItemResponse> updateVoteItem(@RequestBody UpdateVoteItemRequest request, @CookieValue String access) {
    //todo: token에서 updator 가져오기
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long memberId = Long.parseLong(claims.get("memberId").toString());

    VoteItem updatedVoteItem = voteItemService.updateVoteItem(request, memberId);
    UpdateVoteItemResponse updateVoteItemResponse = UpdateVoteItemResponse.create(updatedVoteItem.getId(), updatedVoteItem.getVoteItemName());
    return ResponseEntity.ok().body(updateVoteItemResponse);
  }

  private String returnAccessToken(String fullToken){
    String parseToken = "";
    if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
      parseToken = fullToken.substring(7);
    }
    return parseToken;
  }
}
