package com.project.planit.chatting.dto;

import com.project.planit.member.entity.Role;
import com.project.planit.util.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreateChattingRequest {
    @NotNull
    private String message;

    @NotNull
    private Long roomId;

    @NotNull
    private BaseRequest baseRequest;
}
