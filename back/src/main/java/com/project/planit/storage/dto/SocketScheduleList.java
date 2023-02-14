package com.project.planit.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.project.planit.storage.dto
 * fileName       : SocketScheduleItem
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
public class SocketScheduleList {
    @NotNull
    private LocalDateTime date;
    @NotNull
    private List<SocketScheduleListItem> items;
}
