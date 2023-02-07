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
<<<<<<< HEAD
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
=======
 * packageName    : com.project.planit.vote.repository fileName       : VoteRepository author :
 * Gukss date           : 2023-01-25 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2023-01-25        Gukss       최초 생성
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  //Todo: 이름 바꾸기; 리스트를 받아오려면 findAll해야한다.

  Optional<List<Vote>> findAllByRoom(Room room);


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
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
