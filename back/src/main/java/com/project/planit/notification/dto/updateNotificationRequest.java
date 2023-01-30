package com.project.planit.notification.dto;

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
public class updateNotificationRequest {
    @NotNull
    private boolean readOrNot;

    @NotNull
    private Long notificationId;

    @NotNull
    private BaseRequest baseRequest;
}
