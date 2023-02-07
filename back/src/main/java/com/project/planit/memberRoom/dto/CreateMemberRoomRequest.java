package com.project.planit.memberRoom.dto;

import com.project.planit.util.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMemberRoomRequest {
<<<<<<< HEAD
=======

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    @NotNull
    private Long roomId;
    @NotNull
    private String invitedName;
    @NotNull
    private BaseRequest baseRequest;
}
