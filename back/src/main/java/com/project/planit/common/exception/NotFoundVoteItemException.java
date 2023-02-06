package com.project.planit.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.project.planit.common.exception fileName       : NotFoundVoteItemException
 * author         : Gukss date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        Gukss       최초
 * 생성
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "존재하지 않는 투표항목입니다.")
public class NotFoundVoteItemException extends RuntimeException{

  public NotFoundVoteItemException() {
    super();
  }

  public NotFoundVoteItemException(String message) {
    super(message);
  }

  public NotFoundVoteItemException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundVoteItemException(Throwable cause) {
    super(cause);
  }

  protected NotFoundVoteItemException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
