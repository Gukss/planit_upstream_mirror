package com.project.planit.voteItem.dto;

import lombok.*;

/**
 * packageName    : com.project.planit.voteItem.dto
 * fileName       : FindVoteItemListResponse
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class FindVoteItemResponse {
    private Long voteId;
    private Long voteItemId;
    private String voteItemName;
}
