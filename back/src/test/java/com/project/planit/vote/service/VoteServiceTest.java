package com.project.planit.vote.service;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.room.service.RoomService;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.util.BaseEntity;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  VoteServiceImpl voteService;

  @Autowired
  VoteRepository voteRepository;
  @Autowired
  RoomServiceImpl roomService;

  @Autowired
  RoomRepository roomRepository;

  @Autowired
  EntityManager em;

  @Test
  @DisplayName("투표생성")
//  @Rollback(false)
  void 투표생성() throws Exception {
    //given
    CreateVoteRequest request = CreateVoteRequest.builder()
            .room(Room.builder().roomName("방이름").startDate(LocalDate.now()).endDate(LocalDate.now()).build())
            .title("투표이름")
            .build();
    //when
    Vote newVote = voteService.createVote(request);

    //then
    em.flush();
    assertEquals(newVote, voteRepository.findById(newVote.getId()).get());
  }

  @Test
  @DisplayName("투표조회")
//  @Rollback(false)
  void 투표조회() throws Exception {
    //given
//    Long roomId = 1L;
    CreateRoomRequest roomRequest = CreateRoomRequest.builder()
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .roomName("새로운 방 이름")
            .build();
    Room newRoom = roomService.createRoom(roomRequest);

    CreateVoteRequest voteRequest = CreateVoteRequest.builder()
            .room(newRoom)
            .title("새로운 투표 제목")
            .build();
    Vote newVote = voteService.createVote(voteRequest);
    //when
    List<Vote> foundVotes = voteService.findByRoom(newRoom).get();

    //then
    em.flush();
    assertEquals(newVote, foundVotes.get(0));
  }
}