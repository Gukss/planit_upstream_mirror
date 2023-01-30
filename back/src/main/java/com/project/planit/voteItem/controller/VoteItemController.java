package com.project.planit.voteItem.controller;

import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.service.VoteService;
import com.project.planit.vote.service.VoteServiceImpl;
import com.project.planit.voteItem.dto.*;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.service.VoteItemServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  @PostMapping
  public ResponseEntity<CreateVoteItemResponse> createVoteItem(@RequestBody CreateVoteItemRequest request){
    VoteItem newVoteItem = voteItemService.createVoteItem(request);
    CreateVoteItemResponse createVoteItemResponse = CreateVoteItemResponse.create(
        newVoteItem.getId(), newVoteItem.getVoteItemName());
    return ResponseEntity.ok()
        .body(createVoteItemResponse);
  }

  @GetMapping(path = "{voteId}")
  public ResponseEntity<FindVoteItemListResponse> findVoteItemByVote(@PathVariable Long voteId){
    Vote foundVote = voteService.findById(voteId);
    List<VoteItem> foundVoteItems = voteItemService.findAllByVote(foundVote);

    return ResponseEntity.ok()
            .body(FindVoteItemListResponse.create(foundVoteItems));
  }

  @PatchMapping
  public ResponseEntity<UpdateVoteItemResponse> updateVoteItem(@RequestBody UpdateVoteItemRequest request){
    VoteItem updatedVoteItem = voteItemService.updateVoteItem(request);
    UpdateVoteItemResponse updateVoteItemResponse = UpdateVoteItemResponse.create(updatedVoteItem.getId(), updatedVoteItem.getVoteItemName());
    return ResponseEntity.ok().body(updateVoteItemResponse);
  }
}
