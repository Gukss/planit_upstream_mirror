package com.project.planit.member.repository;

import com.project.planit.member.entity.Member;
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
  @Override
  <S extends Member> S save(S entity);
}
