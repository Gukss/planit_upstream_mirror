package com.project.planit.vote.service;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.dto.UpdateVoteRequest;
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
 * : Gukss date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        Gukss       최초
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
        //when
        Vote newVote = makeVote();

        //then
        em.flush();
        assertEquals(newVote, voteRepository.findById(newVote.getId()).get());
    }

    @Test
    @DisplayName("방으로투표들조회")
//  @Rollback(false)
    void 방으로투표들조회() throws Exception {
        //given
        //request 만들기
        CreateRoomRequest roomRequest = CreateRoomRequest.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .roomName("새로운 방 이름")
                .baseRequest(makeBaseRequest())
                .build();
        //만든 request로 방 만들기
        Room newRoom = roomService.createRoom(roomRequest, 1L);

        //만든 방에 투표 만들기
        CreateVoteRequest voteRequest = CreateVoteRequest.builder()
                .roomId(newRoom.getId())
                .title("새로운 투표 제목")
                .baseRequest(makeBaseRequest())
                .build();
        Vote newVote = voteService.createVote(voteRequest, 1L);

        //when
        //방에 있는 투표를 모두 조회
        List<Vote> foundVotes = voteService.findAllByRoom(newRoom);

        //then
        em.flush();
        //만든 투표와 조회한 투표가 같아야한다.
        assertEquals(newVote, foundVotes.get(0));
    }

    @Test
    @DisplayName("투표제목변경")
        //@Rollback(false)
    void 투표제목변경() throws Exception {
        //given
        //방 생성을 위한 request 만들기
        CreateRoomRequest roomRequest = CreateRoomRequest.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .roomName("새로운 방 이름")
                .baseRequest(makeBaseRequest())
                .build();
        //만든 request로 방 만들기
        Room newRoom = roomService.createRoom(roomRequest, 1L);

        //만든 방에 투표 만들기
        CreateVoteRequest voteRequest = CreateVoteRequest.builder()
            .roomId(newRoom.getId())
                .title("새로운 투표 제목")
                .baseRequest(makeBaseRequest())
                .build();
        Vote newVote = voteService.createVote(voteRequest, 1L);

        //투표 제목 변경을 위한 updateRequest 만들기
        UpdateVoteRequest updateRequest = UpdateVoteRequest.builder()
                .voteId(newVote.getId())
                .newTitle("변경된 투표 제목")
                .build();

        //when
        Vote updatedVote = voteService.updateVote(updateRequest, 1L);

        //then
        em.flush();
        //업데이트된 투표의 제목과 request로 들어온 제목이 같아야한다.
        assertEquals(updatedVote.getTitle(), newVote.getTitle());
    }

    @Test
    @DisplayName("아이디로투표조회")
        //@Rollback(false)
    void 아이디로투표조회() throws Exception {
        //given
        //when
        Vote newVote = makeVote();

        //then
        em.flush();
        assertEquals(newVote.getId(), voteRepository.findById(newVote.getId()).get().getId());
    }

    //====== method =======

    private Vote makeVote() {
        CreateVoteRequest request = CreateVoteRequest.builder()
                .roomId(1L)
                .title("투표이름")
                .baseRequest(makeBaseRequest())
                .build();
        //when
        return voteService.createVote(request, 1L);
    }

    private BaseRequest makeBaseRequest() {
        return BaseRequest.builder()
                .constructor("Gukss")
                .updater("Gukss")
                .build();
    }
}