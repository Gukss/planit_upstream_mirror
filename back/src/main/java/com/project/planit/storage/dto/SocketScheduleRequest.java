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
 * fileName       : SocketScheduleRequest
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
public class SocketScheduleRequest {
    @NotNull
    private List<SocketScheduleList> presentSche;
    @NotNull
    private Long roomId;
}
