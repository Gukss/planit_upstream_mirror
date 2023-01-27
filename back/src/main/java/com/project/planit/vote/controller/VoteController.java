package com.project.planit.vote.controller;

import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.dto.CreateVoteResponse;
import com.project.planit.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  private final VoteService voteService;

  @PostMapping
  public CreateVoteResponse getMapping(@RequestBody CreateVoteRequest request){
//    log.info("vote controller");
    Long id = voteService.createVote(request);
    return new CreateVoteResponse(id);
  }
}
