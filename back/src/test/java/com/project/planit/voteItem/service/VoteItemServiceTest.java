//package com.project.planit.voteItem.service;
//
//import com.project.planit.room.entity.Room;
//import com.project.planit.room.repository.RoomRepository;
//import com.project.planit.room.service.RoomServiceImpl;
//import com.project.planit.util.BaseRequest;
//import com.project.planit.vote.dto.CreateVoteRequest;
//import com.project.planit.vote.dto.UpdateVoteRequest;
//import com.project.planit.vote.entity.Vote;
//import com.project.planit.vote.repository.VoteRepository;
//import com.project.planit.vote.service.VoteServiceImpl;
//import com.project.planit.voteItem.dto.CreateVoteItemRequest;
//import com.project.planit.voteItem.dto.UpdateVoteItemRequest;
//import com.project.planit.voteItem.entity.VoteItem;
//import com.project.planit.voteItem.repository.VoteItemRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import org.springframework.test.annotation.Rollback;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * packageName    : com.project.planit.voteItem.service
// * fileName       : VoteItemServiceImplTest
// * author         : dongk
// * date           : 2023-01-29
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2023-01-29        dongk       최초 생성
// */
//@SpringBootTest
//@Transactional
//class VoteItemServiceTest {
//    @Autowired
//    VoteItemServiceImpl voteItemService;
//
//    @Autowired
//    VoteItemRepository voteItemRepository;
//
//    @Autowired
//    VoteServiceImpl voteService;
//
//    @Autowired
//    VoteRepository voteRepository;
//    @Autowired
//    RoomServiceImpl roomService;
//
//    @Autowired
//    RoomRepository roomRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    @DisplayName("투표항목생성")
//    //@Rollback(false)
//    void 투표항목생성() throws Exception {
//        //given
//        //when
//        VoteItem newVoteItem = makeVoteItem(makeVote());
//
//        //then
//        em.flush();
//        assertEquals(newVoteItem, voteItemRepository.findById(newVoteItem.getId()).get());
//    }
//
//    @Test
//    @DisplayName("투표로투표항목들조회")
////    @Rollback(false)
//    void 투표로투표항목들조회() throws Exception {
//        //given
//        Vote newVote = makeVote();
//        VoteItem newVoteItem = makeVoteItem(newVote);
//        //when
//        List<VoteItem> foundVoteItemList = voteItemService.findAllByVote(newVote);
//        //then
//        em.flush();
//        assertEquals(newVoteItem.getId(), foundVoteItemList.get(0).getId());
//    }
//
//    @Test
//    @DisplayName("투표이름갱신")
//    void 투표이름갱신() throws Exception {
//        //given
//        Vote newVote = makeVote();
//        VoteItem newVoteItem = makeVoteItem(newVote);
//
//        //변경을 위한 request
//        UpdateVoteItemRequest updateRequest = UpdateVoteItemRequest.builder()
//            .voteItemId(newVoteItem.getId())
//            .newVoteItemName("변경된 투표항목 이름")
//            .baseRequest(makeBaseRequest())
//            .build();
//        //when
//        VoteItem updatedVoteItem = voteItemService.updateVoteItem(updateRequest);
//        //then
//        em.flush();
//        assertEquals(updatedVoteItem.getVoteItemName(), newVoteItem.getVoteItemName());
//    }
//
//    //========= method ========
//
//    private VoteItem makeVoteItem(Vote vote) {
//        CreateVoteItemRequest request = CreateVoteItemRequest.builder()
//                .voteItemName("새로운 투표 항목")
//                .baseRequest(makeBaseRequest())
//                .voteId(vote.getId())
//                .build();
//        return voteItemService.createVoteItem(request, 1L);
//    }
//
//    private BaseRequest makeBaseRequest(){
//        return BaseRequest.builder()
//                .constructor("Gukss")
//                .updater("Gukss")
//                .build();
//    }
//
//    private Vote makeVote(){
//        CreateVoteRequest request = CreateVoteRequest.builder()
//            .roomId(1L)
////                .baseRequest(makeBaseRequest())
//                .build();
//        //when
//        return voteService.createVote(request, 1L);
//    }
//}
