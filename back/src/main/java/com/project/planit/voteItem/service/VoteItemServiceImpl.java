package com.project.planit.voteItem.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundVoteException;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
=======
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.vote.entity.Vote;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.vote.service.VoteService;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.dto.UpdateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD

import java.util.List;
=======
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

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
<<<<<<< HEAD
 * 2023-02-03        Gukss       예외 처리
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteItemServiceImpl implements VoteItemService {

    private final VoteItemRepository voteItemRepository;
<<<<<<< HEAD
    private final VoteRepository voteRepository;

    @Override
    @Transactional
    public VoteItem createVoteItem(CreateVoteItemRequest request) {
        Long voteId = request.getVoteId();

        String voteItemName = request.getVoteItemName();
        Vote vote = voteRepository.findById(voteId).orElseThrow(()->new NotFoundVoteException(NotFoundExceptionMessage.VOTE_NOT_FOUND));
        //todo: constructor, updator 토큰에서 가지고 오기
        String constructor = "Gukss";
        String updator = "Gukss";
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
=======
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
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }

    @Override
    @Transactional
    //todo: service에 모두 transactional 달려있는지 확인하기
    public VoteItem updateVoteItem(UpdateVoteItemRequest request) {
<<<<<<< HEAD
        VoteItem targetVoteItem = voteItemRepository.findById(request.getVoteItemId()).orElseThrow(
            ()->new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_NOT_FOUND));
=======
        VoteItem targetVoteItem = voteItemRepository.findById(request.getVoteItemId()).get();
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
        targetVoteItem.changeVoteItemName(request.getNewVoteItemName());
        return targetVoteItem;
    }
}
