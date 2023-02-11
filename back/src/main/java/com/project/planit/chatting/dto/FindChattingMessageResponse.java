package com.project.planit.chatting.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.project.planit.chatting.dto
 * fileName       : FindChattingMessageResponse
 * author         : dongk
 * date           : 2023-02-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-02-04        dongk       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@AllArgsConstructor
@Builder
public class FindChattingMessageResponse {
    private String message;
    private String constructor;
    private LocalDateTime createdAt;
}
