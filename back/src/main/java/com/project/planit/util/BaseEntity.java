package com.project.planit.util;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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

  @CreatedDate
  @NotBlank
  private LocalDateTime created_at; //생성일자

  @LastModifiedDate
  @NotBlank
  private LocalDateTime updated_date; //수정일자
}
