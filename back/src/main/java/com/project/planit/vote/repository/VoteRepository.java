package com.project.planit.vote.repository;

import com.project.planit.room.entity.Room;
import com.project.planit.vote.entity.Vote;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.project.planit.vote.repository
 * fileName       : VoteRepository
 * author         : Gukss
 * date           : 2023-01-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR NOTE
 * -----------------------------------------------------------
 * 2023-01-25        Gukss       최초 생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  Optional<List<Vote>> findAllByRoom(Room room);
}
