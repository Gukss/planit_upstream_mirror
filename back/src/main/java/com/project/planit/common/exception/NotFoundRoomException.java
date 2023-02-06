package com.project.planit.common.exception;

/**
 * packageName    : com.project.planit.common.exception
 * fileName       : NotFoundRoomException
 * author         : Gukss
 * date           : 2023-02-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR    NOTE
 * -----------------------------------------------------------
 * 2023-02-03        Gukss       최초생성
 * 2023-02-03        Gukss       예외처리에 맞게 변경
 */
public class NotFoundRoomException extends RuntimeException{
  public static final String ROOM_NOT_FOUND = "존재하지 않는 방입니다.";

}
