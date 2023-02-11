package com.project.planit.room.repository;

import com.project.planit.room.entity.Room;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.project.planit.room.repository
 * fileName       : RoomRepository
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
