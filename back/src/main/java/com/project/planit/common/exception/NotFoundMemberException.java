package com.project.planit.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.project.planit.common.exception fileName       : NotFoundMemberException
 * author         : Gukss date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        Gukss       최초
 * 생성
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "존재하지 않는 회원입니다.")
public class NotFoundMemberException extends RuntimeException{
  public NotFoundMemberException() {
    super();
  }

  public NotFoundMemberException(String message) {
    super(message);
  }
}
