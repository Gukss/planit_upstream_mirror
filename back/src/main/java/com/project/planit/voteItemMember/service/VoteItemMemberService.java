package com.project.planit.voteItemMember.service;

import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import java.util.List;

/**
 * packageName    : com.project.planit.voteItemMember.service
 * fileName       : VoteItemMemberService
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
public interface VoteItemMemberService {
    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request, Long memberId);
    public List<VoteItemMember> findAllByVoteItemIdAndMemberId(Long voteItemId, Long MemberId);
}
