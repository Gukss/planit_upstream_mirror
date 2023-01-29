package com.project.planit.voteItem.service;

import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.room.service.RoomServiceImpl;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.dto.CreateVoteRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import com.project.planit.vote.service.VoteServiceImpl;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.project.planit.voteItem.service
 * fileName       : VoteItemServiceImplTest
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
@SpringBootTest
@Transactional
class VoteItemServiceImplTest {
    @Autowired
    VoteItemServiceImpl voteItemService;

    @Autowired
    VoteItemRepository voteItemRepository;

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
    @DisplayName("투표항목생성")
    //@Rollback(false)
    void 투표항목생성() throws Exception {
        //given
        //when
        VoteItem newVoteItem = makeVoteItem();

        //then
        em.flush();
        assertEquals(newVoteItem, voteItemRepository.findById(newVoteItem.getId()).get());
    }

    private VoteItem makeVoteItem() {
        CreateVoteItemRequest request = CreateVoteItemRequest.builder()
                .voteItemName("새로운 투표 항목")
                .baseRequest(makeBaseRequest())
                .vote(makeVote())
                .build();
        return voteItemService.createVoteItem(request);
    }

    private BaseRequest makeBaseRequest(){
        return BaseRequest.builder()
                .constructor("Gukss")
                .updater("Gukss")
                .build();
    }

    private Vote makeVote(){
        CreateVoteRequest request = CreateVoteRequest.builder()
                .room(
                        Room.builder()
                                .roomName("방이름")
                                .startDate(LocalDate.now())
                                .endDate(LocalDate.now())
                                .baseRequest(makeBaseRequest())
                                .build())
                .title("투표이름")
                .baseRequest(makeBaseRequest())
                .build();
        //when
        return voteService.createVote(request);
    }
}
