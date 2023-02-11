package com.project.planit.memberRoom.dto;

import com.project.planit.util.BaseRequest;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.member.dto fileName       : updateMemberRequest author
 *  : SSAFY date           : 2023-01-28 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-28        SSAFY       최초
 * 생성
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UpdateMemberRoomRequest {
  @NotNull
  private Long roomId;
  @NotNull
  private Boolean participation;
  @NotNull
  private BaseRequest baseRequest;
}
