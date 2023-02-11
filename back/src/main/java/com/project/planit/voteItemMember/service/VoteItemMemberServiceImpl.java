package com.project.planit.voteItemMember.service;

import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundMemberRoomException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.project.planit.voteItemMember.service
 * fileName       : CreateVoteItemMemberRequestImpl
 * author         : Gukss
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        Gukss       최초 생성
 * 2023-01-30        Gukss       예외처리, 반환값 수정
 *
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
    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request, Long memberId) {
        //todo: memberId는 토큰에서 받아온다. 변경하기 => O
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));
        //todo: BaseRequest 내용도 토큰에서 appId 받아오기 => O
        String constructor = member.getAppId();
        String updater = member.getAppId();
        BaseRequest baseRequest = BaseRequest.builder()
            .constructor(constructor)
            .updater(updater)
            .build();
        Long voteItemId = request.getVoteItemId();

        VoteItem voteItem = voteItemRepository.findById(voteItemId)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_LIST_NOT_FOUND));

        VoteItemMember newVoteItemMember = VoteItemMember.create(baseRequest, member, voteItem);
        return voteItemMemberRepository.save(newVoteItemMember);
    }

    @Override
    public List<VoteItemMember> findAllByVoteItemIdAndMemberId(Long voteItemId, Long MemberId) {
        VoteItem foundVoteItem = voteItemRepository.findById(voteItemId)
            .orElseThrow(() -> new NotFoundExceptionMessage(
                NotFoundExceptionMessage.VOTE_ITEM_NOT_FOUND));

        Member foundMember = memberRepository.findById(MemberId).orElseThrow(()->new NotFoundExceptionMessage(
            NotFoundExceptionMessage.USER_NOT_FOUND));

        return voteItemMemberRepository.findAllByVoteItemAndMember(foundVoteItem, foundMember)
            .orElseThrow(()->new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_MEMBER_LIST_NOT_FOUND));
    }
}
