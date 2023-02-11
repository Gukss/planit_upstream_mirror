package com.project.planit.notification.dto;

import com.project.planit.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreateNotificationRequest {
    @NotNull
    private String receiverMemberId;
    @NotNull
    private Long roomId;
}
