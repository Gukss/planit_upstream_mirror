package com.project.planit.member.controller;

import com.project.planit.common.jwt.JwtService;
import com.project.planit.member.dto.SignInMemberRequest;
import com.project.planit.member.dto.UpdateMemberRequest;
import com.project.planit.member.entity.Member;
import com.project.planit.member.service.MemberService;
import com.project.planit.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.member.controller fileName       : SignInController author
 *      : SSAFY date           : 2023-02-08 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-02-08        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-in")
public class SignInController {

  private final MemberServiceImpl memberService;
  private final JwtService jwtService;

  @PostMapping()
  public ResponseEntity<String> signInMember(@RequestBody SignInMemberRequest request){
    String requestMemberAppId = request.getMemberAppId();
    String requestMemberAppPwd = request.getMemberAppPwd();
    Member foundMember = memberService.findMemberByMemberAppId(requestMemberAppId);
    String foundMemberAppId = foundMember.getAppId();
    String foundMemberAppPwd = foundMember.getAppPwd();

    ResponseEntity res = null;

    if(foundMemberAppId.equals(requestMemberAppId) && foundMemberAppPwd.equals(foundMemberAppPwd)){ //일치
      //토큰 발급
      Long id = foundMember.getId();
      String memberName = foundMember.getName();
      String newToken = jwtService.createToken(id, foundMemberAppId, memberName, 30*1000*60);
      res = ResponseEntity.ok().body(newToken);
    }else{ //불일치
      res = ResponseEntity.badRequest().body("로그인에 실패했습니다.");
    }
    return res;
  }
}
