package com.project.planit.voteItemMember.service;

import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.member.service.MemberService;
import com.project.planit.util.BaseRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import com.project.planit.voteItem.service.VoteItemService;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.test.annotation.Rollback;

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
    @Autowired
    private VoteItemRepository voteItemRepository;

    @Test
    @DisplayName("투표항목_회원_생성")
    @Rollback(false)
    void 투표항목_회원_생성() throws Exception {
        //given
        VoteItemMember voteItemMember = makeVoteItemMember(makeBaseRequest(), returnMemberId(), makeVoteItem().getId());
        //when
        //then
        em.flush();
        assertEquals(voteItemMember, voteItemMemberRepository.findById(voteItemMember.getId()).get());
    }

    @Test
    @DisplayName("투표항목으로조회")
    //@Rollback(false)
    void 투표항목으로조회() throws Exception {
        //given
        VoteItem voteItem = makeVoteItem();
        VoteItemMember voteItemMember = makeVoteItemMember(makeBaseRequest(), returnMemberId(), makeVoteItem().getId());

        //when
        List<VoteItemMember> voteItemMemberList = voteItemMemberService.findAllByVoteItemIdAndMemberId(voteItem.getId(), 1L);
        Long memberId = returnMemberId();
        Member foundMember = memberRepository.findById(memberId).get();
        //then
        assertEquals(voteItemMemberList.get(0), voteItemMemberRepository.findAllByVoteItemAndMember(voteItem, foundMember).get().get(0));
    }

    //======= method =======

    private VoteItemMember makeVoteItemMember(BaseRequest baseRequest, Long memberId, Long voteItemId) {
        return voteItemMemberService.createVoteItemMember(makeRequest(memberId, voteItemId, baseRequest), 1L);
    }

    private Long returnMemberId(){
        //todo: db없이 test만으로 동작하게 변경 필요
        return memberRepository.findById(2L).get().getId();
    }

    private VoteItem makeVoteItem(){
        //todo: db없이 test만으로 동작하게 변경 필요
      return voteItemRepository.findById(1L).get();
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