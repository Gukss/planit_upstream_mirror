package com.project.planit.storage.dto;

import com.project.planit.storage.entity.Category;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.project.planit.storage.dto fileName       : SocketStorageItem author
 * : SSAFY date           : 2023-02-13 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-13        SSAFY       최초
 * 생성
 */
public class SocketStorageItem {

  @NotNull
  private Long id; //이 id 값은 카카오 api의 id값
  @NotNull
  private Category categoryCode; //코드
  @NotNull
  private String categoryName; //관광명소 등
  @NotNull
  private Double y;
  @NotNull
  private Double x;
  @NotNull
  private Boolean isConfirmed;
  @NotNull
  private String title;
  @NotNull
  private String colorCode;

}
