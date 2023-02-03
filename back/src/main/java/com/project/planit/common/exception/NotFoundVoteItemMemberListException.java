package com.project.planit.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.project.planit.common.exception fileName       :
 * NotFoundVoteItemMemberListException author         : Gukss date           : 2023-02-03
 * description    : =========================================================== DATE
 * AUTHOR             NOTE ----------------------------------------------------------- 2023-02-03
 * Gukss       최초 생성
 */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "존재하지 않는 투표항목_회원 리스트입니다.")
public class NotFoundVoteItemMemberListException extends RuntimeException{
//  public static final String VOTE_ITEM_MEMBER_LIST_NOT_FOUND = "존재하지 않는 투표항목_회원 리스트입니다.";

}
