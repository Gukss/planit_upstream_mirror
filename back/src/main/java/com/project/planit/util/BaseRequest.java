package com.project.planit.util;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * packageName    : com.project.planit.util fileName       : BaseDto author         : SSAFY date
 *       : 2023-01-27 description    : ===========================================================
 * DATE              AUTHOR             NOTE
 * ----------------------------------------------------------- 2023-01-27        SSAFY       최초 생성
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
@Builder
public class BaseRequest {
  @NotNull
  private String constructor; //생성자
  @NotNull
  private String updater; //수정자
}
