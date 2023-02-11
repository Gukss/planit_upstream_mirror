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
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class FindNotificationResponse {
  @NotNull
  private String sendMemberName;
  @NotNull
  private String roomName;
  @NotNull
  private LocalDateTime createdAt;
  @NotNull
  private Boolean read;
}
