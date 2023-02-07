package com.project.planit.voteItem.controller;

import com.project.planit.vote.entity.Vote;
<<<<<<< HEAD
=======
import com.project.planit.vote.service.VoteService;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.vote.service.VoteServiceImpl;
import com.project.planit.voteItem.dto.*;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.service.VoteItemServiceImpl;
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
<<<<<<< HEAD
    URI uri = URI.create(""+newVoteItem.getId());
    return ResponseEntity.created(uri)
=======
    return ResponseEntity.ok()
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
        .body(createVoteItemResponse);
  }

  @GetMapping(path = "{voteId}")
<<<<<<< HEAD
  public ResponseEntity<List<FindVoteItemResponse>> findVoteItemByVote(@PathVariable Long voteId){
    Vote foundVote = voteService.findById(voteId);
    List<VoteItem> foundVoteItems = voteItemService.findAllByVote(foundVote);

    List<FindVoteItemResponse> resList = new ArrayList<>();

    for(VoteItem voteItem: foundVoteItems){
      resList.add(voteItem.createFindVoteItemResponse());
    }

    return ResponseEntity.ok().body(resList);
=======
  public ResponseEntity<FindVoteItemListResponse> findVoteItemByVote(@PathVariable Long voteId){
    Vote foundVote = voteService.findById(voteId);
    List<VoteItem> foundVoteItems = voteItemService.findAllByVote(foundVote);

    return ResponseEntity.ok()
            .body(FindVoteItemListResponse.create(foundVoteItems));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  @PatchMapping
  public ResponseEntity<UpdateVoteItemResponse> updateVoteItem(@RequestBody UpdateVoteItemRequest request){
    VoteItem updatedVoteItem = voteItemService.updateVoteItem(request);
    UpdateVoteItemResponse updateVoteItemResponse = UpdateVoteItemResponse.create(updatedVoteItem.getId(), updatedVoteItem.getVoteItemName());
    return ResponseEntity.ok().body(updateVoteItemResponse);
  }
}
