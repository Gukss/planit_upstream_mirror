package com.project.planit.voteItemMember.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.entity.Role;
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
        VoteItemMember voteItemMember = makeVoteItemMember(makeBaseRequest(), returnNewMemberId(), makeVoteItem().getId());
        //when
        //then
        em.flush();
        assertEquals(voteItemMember, voteItemMemberRepository.findById(voteItemMember.getId()).get());
    }

    //======= method =======

    private VoteItemMember makeVoteItemMember(BaseRequest baseRequest, Long memberId, Long voteItemId) {
        return voteItemMemberService.createVoteItemMember(makeRequest(memberId, voteItemId, baseRequest));
    }

    private Long returnNewMemberId(){
        CreateMemberRequest request = CreateMemberRequest.builder()
            .memberAppId("Gukss")
            .memberName("Gukss_name")
            .memberEmail("Gukss@gmail.com")
            .memberAppPwd("Gukss")
            .baseRequest(makeBaseRequest())
            .role(Role.MEMBER)
            .build();
        return memberService.createMember(request);
    }

    private VoteItem makeVoteItem(){
      CreateVoteItemRequest request = CreateVoteItemRequest.builder()
          .voteItemName("성심당")
          .voteId(1L)
          .baseRequest(makeBaseRequest())
          .build();
      return voteItemService.createVoteItem(request);
    }

    private CreateVoteItemMemberRequest makeRequest(Long memberId, Long voteItemId, BaseRequest baseRequest){
        return CreateVoteItemMemberRequest.builder()
            .voteItemId(voteItemId)
            .memberId(memberId)
            .baseRequest(baseRequest)
            .build();
    }

    private BaseRequest makeBaseRequest(){
        return BaseRequest.builder()
                .constructor("Gukss")
                .updater("Gukss")
                .build();
    }
}