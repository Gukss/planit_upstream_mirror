package com.project.planit.storage.dto;

import com.project.planit.util.BaseRequest;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.storage.dto fileName       : UpdateStorageRequest author
 *    : SSAFY date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UpdateStorageRequest {
  @NotNull
  private Long storagesId;
  @NotNull
  private Boolean confirmed;
  @NotNull
  private Integer dayOrder;
  @NotNull
  private Long roomId;
  @NotNull
  private BaseRequest baseRequest;
}
