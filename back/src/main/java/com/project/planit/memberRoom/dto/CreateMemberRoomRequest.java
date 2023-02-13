package com.project.planit.memberRoom.dto;

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
public class CreateMemberRoomRequest {
    @NotNull
    private Long roomId;
    @NotNull
    private String colorCode;
}
