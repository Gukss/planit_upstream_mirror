package com.project.planit.storage.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.storage.dto fileName       : SocketStorageRequest author
 *    : Gukss date           : 2023-02-13 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-13        Gukss       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SocketStorageRequest {
  @NotNull
  private Long roomId;

  @NotNull
  private List<SocketStorageItem> storageItemList;


}
