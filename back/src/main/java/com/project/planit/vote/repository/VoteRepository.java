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
 * packageName    : com.project.planit.vote.repository fileName       : VoteRepository author :
 * Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2023-01-25        Gukss       최초 생성
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  @Override
  <S extends Vote> S save(S entity);

  Optional<Vote> findByRoom(Room room);

  //  @PersistenceContext
//  private EntityManager em;
//
//  /**
//   * Save. methodName : save author : Gukss description :
//   *
//   * @param vote the vote
//   */
//  public void save (Vote vote){
//    em.persist(vote);
//  }
//
//  /**
//   * Find one vote. methodName : findOne author : Gukss description :
//   *
//   * @param id the id
//   * @return the vote
//   */
//  public Vote findOne(Long id){
//    return em.find(Vote.class, id);
//  }
//
//  public List<Vote> findAll(){
//    return em.createQuery("select v from Vote v", Vote.class)
//        .getResultList();
//  }
//
//  //todo: room_id로 조회인데 parameter가 Long id가 맞는지, Room room이 맞는지 확인하기
//  public List<Vote> findByRoomId(Long id){
//    return em.createQuery("select v from Vote v where v.room = :id", Vote.class)
//        .setParameter("id", id)
//        .getResultList();
//  }
}