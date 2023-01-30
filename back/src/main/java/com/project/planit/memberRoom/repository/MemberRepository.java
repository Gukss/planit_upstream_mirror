package com.project.planit.memberRoom.repository;

import com.project.planit.memberRoom.entity.MemberRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.project.planit.memberRoom.repository fileName       : MemberRepository
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
public interface MemberRepository extends JpaRepository<MemberRoom,Long> {

}
