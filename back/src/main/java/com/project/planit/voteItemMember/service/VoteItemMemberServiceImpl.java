package com.project.planit.voteItemMember.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
import javax.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.project.planit.voteItemMember.service
 * fileName       : CreateVoteItemMemberRequestImpl
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteItemMemberServiceImpl implements VoteItemMemberService {

    private final VoteItemMemberRepository voteItemMemberRepository;
    private final MemberRepository memberRepository;
    private final VoteItemRepository voteItemRepository;

    @Override
    @Transactional
    //todo: memberId는 토큰에서 받아온다. 변경하기
    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request) {
        Long memberId = request.getMemberId();
        BaseRequest baseRequest = request.getBaseRequest();
        Long voteItemId = request.getVoteItemId();

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));
        VoteItem voteItem = voteItemRepository.findById(voteItemId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.VOTE_ITEM_LIST_NOT_FOUND));

        VoteItemMember newVoteItemMember = VoteItemMember.create(baseRequest, member, voteItem);
        return voteItemMemberRepository.save(newVoteItemMember);
    }
}
