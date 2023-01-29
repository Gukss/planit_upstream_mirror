package com.project.planit.util;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * packageName    : com.project.planit.util fileName       : BaseEntity author         : Gukss date
 *          : 2023-01-20 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-20        Gukss       최초
 * 생성
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @CreatedDate
  @NotNull
  private LocalDateTime created_at; //생성일자

  @LastModifiedDate
  @NotNull
  private LocalDateTime updated_date; //수정일자
}
