package com.project.planit.member.repository;

import com.project.planit.member.entity.Member;
import com.project.planit.notification.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.project.planit.member.repository fileName       : MemberRepository author
 *      : SSAFY date           : 2023-01-26 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-26        SSAFY       최초
 * 생성
 */
public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByAppId(String memberAppId);
  Optional<Member> findByEmail(String memberEmail);
  Optional<Member> findByAppIdAndEmail(String memberAppId,String memberEmail);
  List<Member> findAllByAppId(String memberAppId);

}
