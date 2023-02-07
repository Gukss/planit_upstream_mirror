package com.project.planit.member.controller;

import com.project.planit.member.dto.CreateMemberRequest;
<<<<<<< HEAD
import com.project.planit.member.dto.ReadMemberResponse;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.member.dto.UpdateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.service.MemberServiceImpl;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
<<<<<<< HEAD
  // TODO: 회원가입 부분은 토큰을 반환할것이여서 다시 만져야함
  @PostMapping()
  public ResponseEntity<?> createMember(@RequestBody CreateMemberRequest request){
    // @TODO : 토큰 아이디로 변경
=======
  @PostMapping()
  public ResponseEntity<?> createMember(@RequestBody CreateMemberRequest request){
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    Long id=memberService.createMember(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 회원 정보 수정
  @PatchMapping()
<<<<<<< HEAD
  public ResponseEntity<String> updateMember(@RequestBody UpdateMemberRequest request){
    Long id=1L; // @TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함

    memberService.updateMember(id,request);

    return ResponseEntity.ok("ok");
=======
  public ResponseEntity<?> updateMember(@RequestBody UpdateMemberRequest request){
    Long id=1L; // TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함
    Member updateMember=memberService.updateMember(id,request);
    return new ResponseEntity<>(HttpStatus.OK);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  // 회원 정보 조회 (토큰에 있는 아이디로 받아올거임)
  @GetMapping()
<<<<<<< HEAD
  public ResponseEntity<ReadMemberResponse> readMember(){
    Long id =1L; // @TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함
    String memberId="sksn12"; // 토큰에 있는 아이디 값으로 변경할거임
    ReadMemberResponse findMember=memberService.readMember(id);

    return  ResponseEntity.ok(findMember);
=======
  public ResponseEntity<?> readMember(){
    Long id =1L; // TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함
    return ResponseEntity.ok(memberService.readMember(id));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  // 회원 목록 조회 // TODO : ( 반환 어떻게 할지 다시보기!!!)
  @GetMapping(path = "/{memberAppId}")
<<<<<<< HEAD
  public ResponseEntity<List<ReadMemberResponse>> readMemberList(@PathVariable String memberAppId){
    List<ReadMemberResponse> memberList=memberService.readMemberListByMemberId(memberAppId);

    return ResponseEntity.ok(memberList);
=======
  public ResponseEntity<?> readMemberList(@PathVariable String memberAppId){
    return ResponseEntity.ok(memberService.readMemberListByMemberId(memberAppId));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  // 아이디 중복 검사
  @GetMapping(path = "/id/{memberAppId}")
<<<<<<< HEAD
  public ResponseEntity<Boolean> existMemberId(@PathVariable String memberAppId){
    boolean existMemberId=memberService.existMemberId(memberAppId);

    return ResponseEntity.ok(existMemberId);

=======
  public ResponseEntity<?> existMemberId(@PathVariable String memberAppId){
    Map<String, Boolean> response = new HashMap<>();
    response.put("res",memberService.existMemberId(memberAppId));
    return ResponseEntity.ok(response);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  // 아이디 찾기
  @GetMapping(path = "/id/email/{memberEmail}")
<<<<<<< HEAD
  public ResponseEntity<String> findMemberId(@PathVariable String memberEmail){
    String memberId=memberService.findMemberIdByMemberEmail(memberEmail);

    return ResponseEntity.ok(memberId);
=======
  public ResponseEntity<?> findMemberId(@PathVariable String memberEmail){
    return ResponseEntity.ok(memberService.findMemberIdByMemberEmail(memberEmail));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }

  // 비밀번호 찾기
  @GetMapping(path = "/password/{memberAppId}/{memberEmail}")
<<<<<<< HEAD
  public ResponseEntity<String> findMemberPwd(@PathVariable String memberAppId,@PathVariable String memberEmail){
    String memberPwd=memberService.findMemberPwdByMemberIdAndMemberEmail(memberAppId,memberEmail);

    return ResponseEntity.ok(memberPwd);
=======
  public ResponseEntity<?> findMemberPwd(@PathVariable String memberAppId,@PathVariable String memberEmail){
    return ResponseEntity.ok(memberService.findMemberPwdByMemberIdAndMemberEmail(memberAppId,memberEmail));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
  }
}
