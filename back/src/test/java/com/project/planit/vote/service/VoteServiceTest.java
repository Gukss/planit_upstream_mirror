package com.project.planit.vote.service;

import static org.junit.jupiter.api.Assertions.*;

import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

/**
 * packageName    : com.project.planit.vote.service fileName       : VoteServiceTest author
 * : SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
@SpringBootTest
@Transactional
class VoteServiceTest {

  @Autowired
  VoteService voteService;

  @Autowired
  VoteRepository voteRepository;

  @Autowired
  EntityManager em;

  @Test
  @DisplayName("투표생성")
//  @Rollback(false)
  void 투표생성() throws Exception {
      //given
//    Vote vote = new Vote();
      //when
//    Long voteId = voteService.createVote(vote);

    //then
    em.flush();
//    assertEquals(vote, voteRepository.findOne(voteId));
  }
}