package com.project.planit.util;

import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.project.planit.util fileName       : ControllerAdvisor author         :
 * Gukss date           : 2023-02-03 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-03        Gukss       최초
 * 생성
 */
@ControllerAdvice
@Builder
public class ControllerAdvisor {
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ExceptionResponse NotFoundExceptionHandler(Exception e, ServerHttpRequest request){
    return ExceptionResponse.createExceptionResponse(e, HttpStatus.BAD_REQUEST, request);
  }
}
