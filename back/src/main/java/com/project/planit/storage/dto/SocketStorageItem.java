package com.project.planit.storage.dto;

import com.project.planit.storage.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.storage.dto fileName       : SocketStorageItem author
 * : SSAFY date           : 2023-02-13 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-13        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SocketStorageItem {

  @NotNull
  private String id; //이 id 값은 카카오 api의 id값
  @NotNull
  private String categoryCode; //코드
  @NotNull
  private String categoryName; //관광명소 등
  @NotNull
  private String y;
  @NotNull
  private String x;
  @NotNull
  private Boolean isConfirmed;
  @NotNull
  private String title;
  @NotNull
  private String colorCode;

}
