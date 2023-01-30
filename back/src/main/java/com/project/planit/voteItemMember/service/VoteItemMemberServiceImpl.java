//package com.project.planit.voteItemMember.service;
//
//import com.project.planit.member.entity.Member;
//import com.project.planit.util.BaseRequest;
//import com.project.planit.voteItem.entity.VoteItem;
//import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
//import com.project.planit.voteItemMember.entity.VoteItemMember;
//import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * packageName    : com.project.planit.voteItemMember.service
// * fileName       : CreateVoteItemMemberRequestImpl
// * author         : dongk
// * date           : 2023-01-30
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2023-01-30        dongk       최초 생성
// */
//@Service
//@Transactional(readOnly=true)
//@RequiredArgsConstructor
//public class VoteItemMemberServiceImpl implements VoteItemMemberService {
//
//    private final VoteItemMemberRepository voteItemMemberRepository;
//
//    @Override
//    @Transactional
//    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request) {
//        Long id = request.getId();
//        BaseRequest baseRequest = request.getBaseRequest();
//        Member member = request.getMember();
//        VoteItem voteItem = request.getVoteItem();
//
//        VoteItemMember newVoteItemMember = VoteItemMember.create(id, baseRequest, member, voteItem);
//        return voteItemMemberRepository.save(newVoteItemMember);
//    }
//}
