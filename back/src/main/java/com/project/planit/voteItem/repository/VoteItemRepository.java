package com.project.planit.voteItem.repository;

import com.project.planit.vote.entity.Vote;
import com.project.planit.voteItem.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.project.planit.voteItem.repository
 * fileName       : VoteItemRepository
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
}
