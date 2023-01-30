package com.project.planit.notification.dto;

import com.project.planit.util.BaseRequest;
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
public class findNotificationResponse {
  @NotNull
  private String sendMemberId;
  @NotNull
  private String receivedMemberId;
  @NotNull
  private LocalDateTime createdAt;
  @NotNull
  private boolean readOrNot;
}
