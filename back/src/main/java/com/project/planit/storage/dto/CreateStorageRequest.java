package com.project.planit.storage.dto;

import com.project.planit.storage.entity.Category;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.storage.dto fileName       : updateStorageRequest author
 *    : SSAFY date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreateStorageRequest {
      @NotNull
      private Long storagesId;
      @NotNull
      private String storageName;
      @NotNull
      private Boolean confirmed;
      @NotNull
      private Double lat;
      @NotNull
      private Double lng;
      @NotNull
      private String dayOrder;
      @NotNull
      private Long indexOrder;
      @NotNull
      @Enumerated(EnumType.STRING)
      private Category categoryName;
      @NotNull
      private Long roomId;

      @NotNull
      private String colorCode;
}
