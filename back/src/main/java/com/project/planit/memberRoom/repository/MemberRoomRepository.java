package com.project.planit.memberRoom.repository;

import com.project.planit.member.entity.Member;
import com.project.planit.memberRoom.entity.MemberRoom;
import com.project.planit.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

/**
 * packageName    : com.project.planit.memberRoom.repository fileName       : MemberRepository
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
public interface MemberRoomRepository extends JpaRepository<MemberRoom,Long> {
<<<<<<< HEAD
    Optional<List<MemberRoom>> findAllByMemberAndRoom(Member member, Room room);
    List<MemberRoom> findAllByMember(Member member);
=======
    List<MemberRoom> findAllByMemberAndRoom(Member member, Room room);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
