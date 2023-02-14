package com.project.planit.memberRoom.service;

import com.project.planit.memberRoom.dto.CreateMemberRoomRequest;
import com.project.planit.memberRoom.dto.UpdateMemberRoomRequest;
import com.project.planit.memberRoom.entity.MemberRoom;


import java.util.List;

/**
 * packageName    : com.project.planit.memberRoom.service fileName       : MemberRoomService author
 *        : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
public interface MemberRoomService {
  List<MemberRoom> findMemberRoom(Long id);
  MemberRoom updateMemberRoom(UpdateMemberRoomRequest request, Long memberId);

  MemberRoom createMemberRoom(CreateMemberRoomRequest request, Long memberId);

  List<MemberRoom> findAllMemberRoomByRoomId(Long roomId);
}
