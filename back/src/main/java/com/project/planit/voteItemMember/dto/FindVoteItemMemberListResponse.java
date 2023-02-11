package com.project.planit.voteItemMember.dto;

import com.project.planit.voteItemMember.entity.VoteItemMember;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.voteItemMember.dto fileName       : findVoteItemMember author
 *         : Gukss date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        Gukss       최초
 * 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class FindVoteItemMemberListResponse {
  List<VoteItemMember> foundVoteItemMembers;

  public static FindVoteItemMemberListResponse create(List<VoteItemMember> foundVoteItemMembers){
    return FindVoteItemMemberListResponse.builder()
        .foundVoteItemMembers(foundVoteItemMembers)
        .build();
  }
}
