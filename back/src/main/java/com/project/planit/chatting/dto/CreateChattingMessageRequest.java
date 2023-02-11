package com.project.planit.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreateChattingMessageRequest {
    private Long roomId;

    private String message;
}
