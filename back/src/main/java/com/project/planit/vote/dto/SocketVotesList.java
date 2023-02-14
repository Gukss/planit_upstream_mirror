package com.project.planit.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : SocketVotesItem
 * author         : dongk
 * date           : 2023-02-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-14        dongk       최초 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SocketVotesList {
    @NotNull
    private String title;

    @NotNull
    private List<SocketVotesListItem> votesListItem;
}
