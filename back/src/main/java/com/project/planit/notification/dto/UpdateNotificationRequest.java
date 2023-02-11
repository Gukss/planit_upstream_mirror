package com.project.planit.notification.dto;

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
public class UpdateNotificationRequest {
    @NotNull
    private Boolean read;

    @NotNull
    private Long notificationId;

}
