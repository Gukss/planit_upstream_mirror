//package com.project.planit.voteItemMember.service;
//
//import com.project.planit.util.BaseRequest;
//import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
//import com.project.planit.voteItemMember.entity.VoteItemMember;
//import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * packageName    : com.project.planit.voteItemMember.service
// * fileName       : VoteItemMemberServiceTest
// * author         : dongk
// * date           : 2023-01-30
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2023-01-30        dongk       최초 생성
// */
//@SpringBootTest
//@Transactional
//class VoteItemMemberServiceTest {
//
//    @Autowired
//    VoteItemMemberService voteItemMemberService;
//
//    @Autowired
//    VoteItemMemberRepository voteItemMemberRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    @DisplayName("투표항목_회원_생성")
//    //@Rollback(false)
//    void 투표항목_회원_생성() throws Exception {
//        //given
//        CreateVoteItemMemberRequest createVoteItemMemberRequest = makeVoteItemMember();
//        //when
//
//        //then
//        em.flush();
//        assertEquals(createVoteItemMemberRequest, voteItemMemberRepository.findById());
//    }
//
//    //======= method =======
//
//    private VoteItemMember makeVoteItemMember() {
////        return VoteItemMember.create(1L, 1L, makeBaseRequest());
//    }
//
//    private BaseRequest makeBaseRequest(){
//        return BaseRequest.builder()
//                .constructor("Gukss")
//                .updater("Gukss")
//                .build();
//    }
//}