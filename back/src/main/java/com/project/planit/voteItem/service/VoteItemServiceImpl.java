package com.project.planit.voteItem.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.vote.entity.Vote;
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
    //todo: service에서는 service를 불러서 사용하는게 맞나? 여기서는 repo를 불러도 되는건가?
    private final VoteService voteService;

    @Override
    @Transactional
    //todo: 엔티티에 create 메소드 만들어주고 builder사용하지 않기
    public VoteItem createVoteItem(CreateVoteItemRequest request) {
        VoteItem voteItem= VoteItem.builder()
                .voteItemName(request.getVoteItemName())
                .vote(voteService.findById(request.getVoteId()))
                .baseRequest(request.getBaseRequest())
                .build();
        return voteItemRepository.save(voteItem);
    }

    //todo: 매개변수를 voteId 로 변경해야하는거 아닌가?
    @Override
    public List<VoteItem> findAllByVote(Vote vote) {
        return voteItemRepository.findAllByVote(vote).orElseThrow(
                ()->new NotFoundException(NotFoundException.VOTE_ITEM_LIST_NOT_FOUND));
    }

    @Override
    @Transactional
    //todo: service에 모두 transactional 달려있는지 확인하기
    public VoteItem updateVoteItem(UpdateVoteItemRequest request) {
        VoteItem targetVoteItem = voteItemRepository.findById(request.getVoteItemId()).get();
        targetVoteItem.changeVoteItemName(request.getNewVoteItemName());
        return targetVoteItem;
    }
}
