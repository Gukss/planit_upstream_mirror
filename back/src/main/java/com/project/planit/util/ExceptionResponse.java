package com.project.planit.util;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * packageName    : com.project.planit.util fileName       : ExceptionResponse author         :
 * Gukss date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        Gukss       최초
 * 생성
 */
@Getter
@Builder
public class ExceptionResponse {
  private final LocalDateTime timestamp;
//  private final String path;
  private final String path;
  private final HttpStatus httpStatus;
  private final String message;

  public static ExceptionResponse createExceptionResponse(Exception e, HttpStatus httpStatus, ServerHttpRequest request){
//    LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
    final String message = e.getMessage();
    final String path = request.getPath().pathWithinApplication().value();
    return ExceptionResponse.builder()
        .timestamp(LocalDateTime.now())
        .path(path)
        .httpStatus(httpStatus)
        .message(message)
        .build();
  }
}
