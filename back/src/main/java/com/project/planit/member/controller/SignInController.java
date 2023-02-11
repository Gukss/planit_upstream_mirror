//package com.project.planit.member.controller;
//
//import com.project.planit.common.auth.jwt.JwtAuthenticationFilter;
//import com.project.planit.common.auth.jwt.JwtProvider;
//import com.project.planit.common.auth.util.CookieUtils;
//import com.project.planit.member.dto.SignInMemberRequest;
//import com.project.planit.member.dto.SignInMemberResponse;
//import com.project.planit.member.entity.Member;
//import com.project.planit.member.service.MemberServiceImpl;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * packageName    : com.project.planit.member.controller fileName       : SignInController author
// *      : SSAFY date           : 2023-02-08 description    :
// * =========================================================== DATE              AUTHOR
// * NOTE ----------------------------------------------------------- 2023-02-08        SSAFY       최초
// * 생성
// */
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/sign-in")
//public class SignInController {
//
//  private final MemberServiceImpl memberService;
//
//  @PostMapping()
//  public ResponseEntity<SignInMemberResponse> signInMember(@RequestBody SignInMemberRequest request, HttpServletResponse httpServletResponse){
//    String requestMemberAppId = request.getMemberAppId();
//    String requestMemberAppPwd = request.getMemberAppPwd();
//
////    SignInMemberResponse accessSignInMemberResponse = memberService.createAccessToken(requestMemberAppId,
////        requestMemberAppPwd);
////    SignInMemberResponse refreshaccessSignInMemberResponse = memberService.createRefreshToken(requestMemberAppId,
////        requestMemberAppPwd);
//
//    //tocken 2개 발급해서 하나는 쿠키, 하나는 db에 넣는다.
//    CookieUtils.addCookie(httpServletResponse, refreshaccessSignInMemberResponse.getToken());
//
//    // response header 에도 넣고 응답 객체에도 넣는다.
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + accessSignInMemberResponse.getToken());
//
//    ResponseEntity<SignInMemberResponse> res = ResponseEntity.ok().body(accessSignInMemberResponse);
//    return res;
////    Member foundMember = memberService.findMemberByMemberAppId(requestMemberAppId);
////    String foundMemberAppId = foundMember.getAppId();
////    String foundMemberAppPwd = foundMember.getAppPwd();
////
////    ResponseEntity res = null;
////
////    if(foundMemberAppId.equals(requestMemberAppId) && foundMemberAppPwd.equals(foundMemberAppPwd)){ //일치
////      //토큰 발급
////      Long id = foundMember.getId();
////      String memberName = foundMember.getName();
////      String newToken = jwtProvider.createToken(id, foundMemberAppId, memberName, 30*1000*60);
////      res = ResponseEntity.ok().body(newToken);
////    }else{ //불일치
////      res = ResponseEntity.badRequest().body("로그인에 실패했습니다.");
////    }
////    return res;
//  }
//}
