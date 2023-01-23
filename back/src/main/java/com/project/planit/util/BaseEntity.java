package com.project.planit.util;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : com.project.planit.util fileName       : BaseEntity author         : Gukss date
 *          : 2023-01-20 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-20        Gukss       최초
 * 생성
 */
@Embeddable
@Getter
public class BaseEntity {
  private String constructor; //생성자

  private String updater; //수정자

  private LocalDateTime created_at; //생성일자

  private LocalDateTime updated_date; //수정일자
}
