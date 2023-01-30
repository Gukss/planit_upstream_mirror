package com.project.planit.voteItem.service;

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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteItemServiceImpl implements VoteItemService {

    private final VoteItemRepository voteItemRepository;

    private final VoteService voteService;
    @Override
    @Transactional
    public VoteItem createVoteItem(@RequestBody CreateVoteItemRequest request) {
        VoteItem voteItem= VoteItem.builder()
                .voteItemName(request.getVoteItemName())
                .vote(voteService.findById(request.getVoteId()))
                .baseRequest(request.getBaseRequest())
                .build();
        return voteItemRepository.save(voteItem);
    }

    @Override
    public Optional<List<VoteItem>> findByVote(Vote vote) {
        return voteItemRepository.findByVote(vote);
    }

    @Override
    public Optional<VoteItem> updateVoteItem(UpdateVoteItemRequest request) {
        VoteItem targetVoteItem = voteItemRepository.findById(request.getVoteItemId()).get();
        targetVoteItem.changeVoteItemName(request.getNewVoteItemName());
        return Optional.of(targetVoteItem);
    }
}
