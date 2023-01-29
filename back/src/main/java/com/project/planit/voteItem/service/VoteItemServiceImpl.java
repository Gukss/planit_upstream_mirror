package com.project.planit.voteItem.service;

import com.project.planit.vote.service.VoteService;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    @Transactional
    public VoteItem createVoteItem(@RequestBody CreateVoteItemRequest request) {
        VoteItem voteItem= VoteItem.builder()
                .voteItemName(request.getVoteItemName())
                .vote(request.getVote())
                .baseRequest(request.getBaseRequest())
                .build();
        return voteItemRepository.save(voteItem);
    }
}
