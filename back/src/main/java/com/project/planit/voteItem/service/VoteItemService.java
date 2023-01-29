package com.project.planit.voteItem.service;

import com.project.planit.voteItem.dto.CreateVoteItemRequest;
import com.project.planit.voteItem.entity.VoteItem;
import org.springframework.web.bind.annotation.RequestBody;

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
    public VoteItem createVoteItem(@RequestBody CreateVoteItemRequest request);

}
