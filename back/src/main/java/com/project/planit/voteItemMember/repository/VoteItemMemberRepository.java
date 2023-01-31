package com.project.planit.voteItemMember.repository;

import com.project.planit.voteItemMember.entity.VoteItemMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.project.planit.voteItemMember.repository
 * fileName       : VoteItemMemberRepository
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
public interface VoteItemMemberRepository  extends JpaRepository<VoteItemMember, Long> {
  Optional<List<VoteItemMember>> findAllByVoteItemId(Long voteItemId);
}
