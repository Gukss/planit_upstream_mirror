package com.project.planit.member.controller;

import com.project.planit.member.dto.createMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


  // 아이디 중복 검사
//  @GetMapping(path = "/id/{userAppId}")
//  public ResponseEntity<?> idCheck(@PathVariable String userAppId){
//
//  }

  // 회원가입
  @PostMapping()
  public ResponseEntity<?> createMember(@RequestBody createMemberRequest request){
    System.out.println("123123123123");
    Long id=memberService.createMember(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
