package com.project.planit.voteItem.service;

import com.project.planit.vote.entity.Vote;
import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.dto.UpdateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.project.planit.voteItem.service
 * fileName       : VoteItemService
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
public interface VoteItemService {
    public VoteItem createVoteItem(CreateVoteItemRequest request, Long memberId);
    public List<VoteItem> findAllByVote(Vote vote);
    public VoteItem updateVoteItem(UpdateVoteItemRequest request, Long memberId);
}
