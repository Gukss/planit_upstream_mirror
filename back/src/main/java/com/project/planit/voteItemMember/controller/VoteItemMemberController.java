package com.project.planit.voteItemMember.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberResponse;
import com.project.planit.voteItemMember.dto.FindVoteItemMemberResponse;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.service.VoteItemMemberServiceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
  private final JwtProvider jwtProvider;

  @PostMapping
  public ResponseEntity<CreateVoteItemMemberResponse> createVoteItemMember(@RequestBody CreateVoteItemMemberRequest request, @RequestHeader("Authorization") String access) {
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long memberId = Long.parseLong(claims.get("memberId").toString());

    VoteItemMember newVoteItemMember = voteItemMemberService.createVoteItemMember(request, memberId);
    CreateVoteItemMemberResponse createVoteItemMemberResponse = CreateVoteItemMemberResponse.create(
        newVoteItemMember.getMember().getId(), newVoteItemMember.getVoteItem().getId());
    URI uri = URI.create(""+newVoteItemMember.getId());
    return ResponseEntity.created(uri).body(createVoteItemMemberResponse);
  }

  @GetMapping(path = "{voteItemId}")
  public ResponseEntity<List<FindVoteItemMemberResponse>> findVoteItemMemberListByVoteItemId(@PathVariable Long voteItemId){
    List<VoteItemMember> foundVoteItemMemberList = voteItemMemberService.findAllByVoteItemIdAndMemberId(voteItemId, 1L);

    List<FindVoteItemMemberResponse> resList = new ArrayList<>();
    for(VoteItemMember voteItemMember: foundVoteItemMemberList){
      resList.add(voteItemMember.createFindVoteItemMemberResponse());
    }

    return ResponseEntity.ok().body(resList);
  }

  private String returnAccessToken(String fullToken){
    String parseToken = "";
    if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
      parseToken = fullToken.substring(7);
    }
    return parseToken;
  }
}
