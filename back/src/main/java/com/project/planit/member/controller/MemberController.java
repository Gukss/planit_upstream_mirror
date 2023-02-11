package com.project.planit.member.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.dto.ReadMemberResponse;
import com.project.planit.member.dto.UpdateMemberRequest;
import com.project.planit.member.service.MemberServiceImpl;
import java.util.List;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.project.planit.member.controller fileName       : MemberController author :
 * youngman date date           : 2023-01-25 description
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2023-01-25        youngman 최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
  private final MemberServiceImpl memberService;
  private final JwtProvider jwtProvider;
  // 회원가입
  // TODO: 회원가입 부분은 토큰을 반환할것이여서 다시 만져야함
  @PostMapping()
  public ResponseEntity<?> createMember(@RequestBody CreateMemberRequest request){
    // TODO : 토큰 아이디로 변경
    Long id=memberService.createMember(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 회원 정보 수정
  @PatchMapping()
  public ResponseEntity<String> updateMember(@RequestBody UpdateMemberRequest request, @CookieValue String access) {
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
//    log.info("회원수정 회원번호"+claims.get("memberId").toString());
    Long id = Long.parseLong(claims.get("memberId").toString());

    memberService.updateMember(id,request);

    return ResponseEntity.ok("ok");
  }

  // 회원 정보 조회 (토큰에 있는 아이디로 받아올거임)
  @GetMapping()
  public ResponseEntity<ReadMemberResponse> readMember(@CookieValue String access) {
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
//    Long id =1L; // @TODO : 헤더 토큰에 있는 멤버 id값으로 넣어줘야함 => O
    Long id = Long.parseLong(claims.get("memberId").toString());
    String memberId= claims.get("memberId").toString();

//    String memberId="sksn12"; // 토큰에 있는 아이디 값으로 변경할거임
    ReadMemberResponse findMember=memberService.readMember(id);

    return  ResponseEntity.ok(findMember);
  }

  // 회원 목록 조회 // TODO : ( 반환 어떻게 할지 다시보기!!!)
  @GetMapping(path = "/{memberAppId}")
  public ResponseEntity<List<ReadMemberResponse>> readMemberList(@PathVariable String memberAppId){
    List<ReadMemberResponse> memberList=memberService.readMemberListByMemberId(memberAppId);

    return ResponseEntity.ok(memberList);
  }

  // 아이디 중복 검사
  @GetMapping(path = "/id/{memberAppId}")
  public ResponseEntity<Boolean> existMemberId(@PathVariable String memberAppId){
    boolean existMemberId=memberService.existMemberId(memberAppId);

    return ResponseEntity.ok(existMemberId);

  }

  // 아이디 찾기
  @GetMapping(path = "/id/email/{memberEmail}")
  public ResponseEntity<String> findMemberId(@PathVariable String memberEmail){
    String memberId=memberService.findMemberIdByMemberEmail(memberEmail);

    return ResponseEntity.ok(memberId);
  }

  // 비밀번호 찾기
  @GetMapping(path = "/password/{memberAppId}/{memberEmail}")
  public ResponseEntity<String> findMemberPwd(@PathVariable String memberAppId,@PathVariable String memberEmail){
    String memberPwd=memberService.findMemberPwdByMemberIdAndMemberEmail(memberAppId,memberEmail);

    return ResponseEntity.ok(memberPwd);
  }

  private String returnAccessToken(String fullToken){
    String parseToken = "";
    if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
      parseToken = fullToken.substring(7);
    }
    return parseToken;
  }
}
