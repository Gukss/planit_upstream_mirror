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
public class UpdateNotificationRequest {
    @NotNull
<<<<<<< HEAD
    private Boolean read;
=======
    private boolean readOrNot;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

    @NotNull
    private Long notificationId;

<<<<<<< HEAD
=======
    @NotNull
    private BaseRequest baseRequest;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
