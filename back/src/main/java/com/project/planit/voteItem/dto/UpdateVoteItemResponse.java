package com.project.planit.voteItem.dto;

import com.project.planit.util.BaseRequest;
import lombok.*;

import javax.persistence.Embedded;

/**
 * packageName    : com.project.planit.voteItem.dto
 * fileName       : UpdateVoteItemResponse
 * author         : dongk
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-30        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class UpdateVoteItemResponse {
    private Long voteItemId;
    private String newVoteItemName;

    public static UpdateVoteItemResponse create(Long voteItemId, String newVoteItemName){
        return UpdateVoteItemResponse.builder()
                .voteItemId(voteItemId)
                .newVoteItemName(newVoteItemName)
                .build();
    }
}
