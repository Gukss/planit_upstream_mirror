package com.project.planit.memberRoom.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.memberRoom.dto fileName       : FindMemberRoomResponse author
 *         : SSAFY date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        SSAFY       최초
 * 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindMemberRoomResponse {
  @NotNull
  private String colorCode;
  @NotNull
  private Boolean participation;
  @NotNull
  private Long memberId;
  @NotNull
  private Long roomId;
}
