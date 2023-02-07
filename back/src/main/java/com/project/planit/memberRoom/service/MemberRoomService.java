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
<<<<<<< HEAD
  MemberRoom updateMemberRoom(UpdateMemberRoomRequest request);

  MemberRoom createMemberRoom(CreateMemberRoomRequest request);
=======
  void updateMemberRoom(UpdateMemberRoomRequest request);

  void createMemberRoom(CreateMemberRoomRequest request);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

}
