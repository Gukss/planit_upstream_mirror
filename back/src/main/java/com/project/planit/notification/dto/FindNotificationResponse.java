package com.project.planit.notification.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.notification.dto fileName       : findNotificationResponse
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindNotificationResponse {
  @NotNull
<<<<<<< HEAD
  private String sendMemberName;
  @NotNull
  private String roomName;
  @NotNull
  private LocalDateTime createdAt;
  @NotNull
  private Boolean read;
=======
  private String sendMemberId;
  @NotNull
  private String receivedMemberId;
  @NotNull
  private LocalDateTime createdAt;
  @NotNull
  private boolean readOrNot;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
