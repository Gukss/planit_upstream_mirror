package com.project.planit.voteItemMember.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.member.service.MemberService;
import com.project.planit.util.BaseRequest;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.service.VoteItemService;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.project.planit.voteItemMember.service
 * fileName       : VoteItemMemberServiceTest
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@SpringBootTest
@Transactional
class VoteItemMemberServiceTest {

    @Autowired
    VoteItemMemberService voteItemMemberService;

    @Autowired
    VoteItemMemberRepository voteItemMemberRepository;

    @Autowired
    VoteItemService voteItemService;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("투표항목_회원_생성")
    //@Rollback(false)
    void 투표항목_회원_생성() throws Exception {
        //given
        VoteItemMember voteItemMember = makeVoteItemMember(makeBaseRequest(), makeMember(), makeVoteItem());
        //when

        //then
        em.flush();
        assertEquals(voteItemMember, voteItemMemberRepository.findById(voteItemMember.getId()).orElseThrow(()->new NotFoundException(NotFoundException.VOTE_ITEM_MEMBER_NOT_FOUND)));
    }

    //======= method =======

    private VoteItemMember makeVoteItemMember(BaseRequest baseRequest, Member member, VoteItem voteItem) {
        return VoteItemMember.create(baseRequest, member, voteItem);
    }

    private Member makeMember(){
        CreateMemberRequest request = CreateMemberRequest.builder()
            .baseRequest(makeBaseRequest())
            .memberName("Gukss")
            .memberAppId("gukss")
            .memberAppPwd("gukss")
            .memberEmail("gukss@gmail.com")
            .build();
        Long memberId = memberService.createMember(request);
        return memberRepository.findById(memberId).orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));
    }

    private VoteItem makeVoteItem(){
      CreateVoteItemRequest request = CreateVoteItemRequest.builder()
          .voteItemName("성심당")
          .voteId(2L)
          .baseRequest(makeBaseRequest())
          .build();
      return voteItemService.createVoteItem(request);
    }

    private BaseRequest makeBaseRequest(){
        return BaseRequest.builder()
                .constructor("Gukss")
                .updater("Gukss")
                .build();
    }
}