package com.project.planit.member.controller;

import com.project.planit.member.dto.createMemberRequest;
import com.project.planit.member.dto.updateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.service.MemberServiceImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.member.controller fileName       : MemberController author :
 * youngman date date           : 2023-01-25 description
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2023-01-25        youngman 최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
  private final MemberServiceImpl memberService;

  // 회원가입
  @PostMapping()
  public ResponseEntity<?> createMember(@RequestBody createMemberRequest request){
    Long id=memberService.createMember(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 회원 정보 수정
  @PatchMapping()
  public ResponseEntity<?> updateMember(@RequestBody updateMemberRequest request){
    Long id=1L; // 나중에 토큰에 있는 값으로 대체
    Member updateMember=memberService.updateMember(id,request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 회원 정보 조회 (토큰에 있는 아이디로 받아올거임)
  @GetMapping()
  public ResponseEntity<?> readMember(){
    Long id =1L;
    return ResponseEntity.ok(memberService.readMember(id));
  }

  // 회원 목록 조회 ( 반환 어떻게 할지 다시보기!!!)
  @GetMapping(path = "/{memberAppId}")
  public ResponseEntity<?> readMemberList(@PathVariable String memberAppId){
    return ResponseEntity.ok(memberService.readMemberListByMemberId(memberAppId));
  }

  // 아이디 중복 검사
  @GetMapping(path = "/id/{memberAppId}")
  public ResponseEntity<?> existMemberId(@PathVariable String memberAppId){
    Map<String, Boolean> response = new HashMap<>();
    response.put("res",memberService.existMemberId(memberAppId));
    return ResponseEntity.ok(response);
  }

  // 아이디 찾기
  @GetMapping(path = "/id/email/{memberEmail}")
  public ResponseEntity<?> findMemberId(@PathVariable String memberEmail){
    return ResponseEntity.ok(memberService.findMemberIdByMemberEmail(memberEmail));
  }

  // 비밀번호 찾기
  @GetMapping(path = "/password/{memberAppId}/{memberEmail}")
  public ResponseEntity<?> findMemberPwd(@PathVariable String memberAppId,@PathVariable String memberEmail){
    return ResponseEntity.ok(memberService.findMemberPwdByMemberIdAndMemberEmail(memberAppId,memberEmail));
  }
}
