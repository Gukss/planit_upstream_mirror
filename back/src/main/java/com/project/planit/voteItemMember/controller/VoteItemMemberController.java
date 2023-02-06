package com.project.planit.voteItemMember.controller;

import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberResponse;
import com.project.planit.voteItemMember.dto.FindVoteItemMemberResponse;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.service.VoteItemMemberServiceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.voteItemMember.controller fileName       :
 * VoteItemMemberController author         : Gukss date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        Gukss       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/votes/vote-item/user")
public class VoteItemMemberController {
  private final VoteItemMemberServiceImpl voteItemMemberService;

  @PostMapping
  public ResponseEntity<CreateVoteItemMemberResponse> createVoteItemMember(@RequestBody CreateVoteItemMemberRequest request){
    VoteItemMember newVoteItemMember = voteItemMemberService.createVoteItemMember(request);
    CreateVoteItemMemberResponse createVoteItemMemberResponse = CreateVoteItemMemberResponse.create(
        newVoteItemMember.getMember().getId(), newVoteItemMember.getVoteItem().getId());
    URI uri = URI.create(""+newVoteItemMember.getId());
    return ResponseEntity.created(uri).body(createVoteItemMemberResponse);
  }

  @GetMapping(path = "{voteItemId}")
  public ResponseEntity<List<FindVoteItemMemberResponse>> findVoteItemMemberListByVoteItemId(@PathVariable Long voteItemId){
    //todo: token값으로 바꾸기
    List<VoteItemMember> foundVoteItemMemberList = voteItemMemberService.findAllByVoteItemIdAndMemberId(voteItemId, 1L);

    List<FindVoteItemMemberResponse> resList = new ArrayList<>();
    for(VoteItemMember voteItemMember: foundVoteItemMemberList){
      resList.add(voteItemMember.createFindVoteItemMemberResponse());
    }

    return ResponseEntity.ok().body(resList);
  }
}
