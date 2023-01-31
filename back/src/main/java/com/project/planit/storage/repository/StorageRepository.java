package com.project.planit.storage.repository;

import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;
import com.project.planit.storage.entity.Storage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.project.planit.storage.repository fileName       : StorageRepository author
 *        : SSAFY date           : 2023-01-31 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-31        SSAFY       최초
 * 생성
 */
public interface StorageRepository extends JpaRepository<Storage,Long> {
    List<Storage> findAllByMemberAndRoom(Member member, Room room);
}
