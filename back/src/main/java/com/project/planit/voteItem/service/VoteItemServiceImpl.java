package com.project.planit.voteItem.service;

import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundVoteException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
import com.project.planit.vote.service.VoteService;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.dto.UpdateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.project.planit.voteItem.service
 * fileName       : VoteItemServiceImpl
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 * 2023-02-03        Gukss       예외 처리
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteItemServiceImpl implements VoteItemService {

    private final VoteItemRepository voteItemRepository;
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public VoteItem createVoteItem(CreateVoteItemRequest request, Long memberId) {
        Long voteId = request.getVoteId();

        String voteItemName = request.getVoteItemName();
        Vote vote = voteRepository.findById(voteId).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        String constructor = member.getAppId();
        String updator = member.getAppId();
        BaseRequest baseRequest = BaseRequest.builder()
                .updater(updator)
                .constructor(constructor)
                .build();

        VoteItem voteItem= VoteItem.create(voteItemName, baseRequest, vote);
        return voteItemRepository.save(voteItem);
    }

    @Override
    public List<VoteItem> findAllByVote(Vote vote) {
        return voteItemRepository.findAllByVote(vote).orElseThrow(
                ()->new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_LIST_NOT_FOUND));
    }

    @Override
    @Transactional
    //todo: service에 모두 transactional 달려있는지 확인하기
    public VoteItem updateVoteItem(UpdateVoteItemRequest request, Long memberId) {
        VoteItem targetVoteItem = voteItemRepository.findById(request.getVoteItemId()).orElseThrow(
            ()->new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        targetVoteItem.update(request, member);

        return targetVoteItem;
    }
}
