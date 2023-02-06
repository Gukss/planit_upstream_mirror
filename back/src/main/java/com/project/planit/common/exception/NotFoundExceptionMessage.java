package com.project.planit.common.exception;

/**
 * packageName    : com.project.common.exception fileName       : NotFoundException author         :
 * SSAFY date           : 2023-01-28 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-28        SSAFY       최초
 * 생성
 */
public class NotFoundExceptionMessage extends RuntimeException{
  public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
  public static final String USER_LIST_NOT_FOUND = "검색어와 일치하는 회원이 없습니다";
  public static final String USER_EMAIL_FIND_NOT_FOUND = "해당 이메일에 존재하는 회원이 없습니다";
  public static final String ROOM_NOT_FOUND = "존재하지 않는 방입니다.";
  public static final String VOTE_NOT_FOUND = "존재하지 않는 투표입니다.";
  public static final String VOTE_ITEM_NOT_FOUND = "존재하지 않는 투표항목입니다.";
  public static final String VOTE_ITEM_LIST_NOT_FOUND = "존재하지 않는 투표항목 리스트입니다.";
  public static final String VOTE_ITEM_MEMBER_NOT_FOUND = "존재하지 않는 투표항목_회원입니다.";
  public static final String VOTE_ITEM_MEMBER_LIST_NOT_FOUND = "존재하지 않는 투표항목_회원 리스트입니다.";
  public static final String MEMBER_ROOM_NOT_FOUND = "방에 참가한 회원이 없습니다";
  public static final String NOTIFICATION_NOT_FOUND = "존재하지 않는 알림입니다.";

  public NotFoundExceptionMessage(String message) {
    super(message);
  }
}
