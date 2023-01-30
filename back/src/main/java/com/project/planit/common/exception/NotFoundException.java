package com.project.planit.common.exception;

/**
 * packageName    : com.project.common.exception fileName       : NotFoundException author         :
 * SSAFY date           : 2023-01-28 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-28        SSAFY       최초
 * 생성
 */
public class NotFoundException extends RuntimeException{
  public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
  public static final String USERLIST_NOT_FOUND = "검색어와 일치하는 회원이 없습니다";
  
  public NotFoundException(String message) {
    super(message);
  }
}
