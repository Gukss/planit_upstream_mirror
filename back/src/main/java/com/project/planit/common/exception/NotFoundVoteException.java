package com.project.planit.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.project.planit.common.exception fileName       : NotFoundVoteException
 * author         : Gukss date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        Gukss       최초
 * 생성
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "존재하지 않는 투표입니다.")
public class NotFoundVoteException extends RuntimeException{

  public NotFoundVoteException() {
    super();
  }

  public NotFoundVoteException(String message) {
    super(message);
  }

  public NotFoundVoteException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundVoteException(Throwable cause) {
    super(cause);
  }

  protected NotFoundVoteException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
