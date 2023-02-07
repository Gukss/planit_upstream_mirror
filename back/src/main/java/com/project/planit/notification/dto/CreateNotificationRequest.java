package com.project.planit.notification.dto;

<<<<<<< HEAD

=======
import com.project.planit.util.BaseRequest;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNotificationRequest {
    @NotNull
    private String receiverMemberId;
<<<<<<< HEAD

=======
    @NotNull
    private BaseRequest baseRequest;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
