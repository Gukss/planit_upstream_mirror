package com.project.planit.util;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * packageName    : com.project.planit.util fileName       : ExceptionResponse author         :
 * SSAFY date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        SSAFY       최초
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

  public static ExceptionResponse createExceptionResponse(HttpStatus httpStatus, ServerHttpRequest request, String message){
    return ExceptionResponse.builder()
        .timestamp(LocalDateTime.now())
        .path(request.getPath().pathWithinApplication().value())
        .httpStatus(httpStatus)
        .message(message)
        .build();
  }
}
