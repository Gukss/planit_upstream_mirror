package com.project.planit.voteItem.controller;

import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.dto.CreateVoteItemResponse;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.service.VoteItemServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.voteItemMember.controller fileName       : VoteItemController
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/votes/vote-item")
public class VoteItemController {

  private final VoteItemServiceImpl voteItemService;
  @PostMapping
  public ResponseEntity<CreateVoteItemResponse> createVoteItem(@RequestBody CreateVoteItemRequest request){
    VoteItem newVoteItem = voteItemService.createVoteItem(request);
    CreateVoteItemResponse createVoteItemResponse = CreateVoteItemResponse.create(
        newVoteItem.getId(), newVoteItem.getVoteItemName());
    return ResponseEntity.ok()
        .body(createVoteItemResponse);
  }
}
