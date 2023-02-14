package com.project.planit.member.entity;

import com.project.planit.common.auth.userDetails.PrincipalDetails;
import com.project.planit.member.dto.CreateMemberRequest;
import com.project.planit.member.dto.UpdateMemberRequest;
import javax.persistence.*;

import com.project.planit.util.BaseEntity;
import javax.validation.constraints.NotNull;

import com.project.planit.util.BaseRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.project.planit.users.entity
 * fileName       : Member
 * author         : dongk
 * date           : 2023-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-20        dongk       최초 생성
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="member")
public class Member extends BaseEntity{

  @Id
  @GeneratedValue
  @Column(name = "member_id", nullable = false) //회원번호
  private Long id;

  @Column(name ="member_app_id", nullable = false,unique = true) //아이디
  private String appId;

  @Column(name ="member_app_pwd", nullable = false) //비밀번호
  private String appPwd;

  @Column(name ="member_name", nullable = false) //닉네임
  private String name;

  @Enumerated(EnumType.STRING) //이넘 타입은 문자열로
  @NotNull
  private Role role; //역할

  @NotNull
  private String email; //이메일

  @Embedded
  @NotNull
  private BaseRequest baseRequest;

  private String refreshToken;

  public static Member create(CreateMemberRequest request){
    Member member=Member.builder()
        .appId(request.getMemberAppId())
        .appPwd(request.getMemberAppPwd())
        .name(request.getMemberName())
        .email(request.getMemberEmail())
        .role(Role.MEMBER)
        .baseRequest(BaseRequest.builder()
            .constructor(request.getMemberAppId())
            .updater(request.getMemberAppId())
            .build())
        .build();
    return member;
  }

  public void update(UpdateMemberRequest request){
    this.appPwd=request.getMemberAppPwd();
    this.name=request.getMemberName();
    this.email=request.getMemberEmail();
    this.baseRequest=BaseRequest.builder()
            .constructor(this.appId)
            .updater(request.getMemberAppId())
            .build();
  }

  public static Member of(PrincipalDetails principalDetails) {
    Member member = principalDetails.getMember();
    return member;
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void updatePwd(String newPwd){
    this.appPwd = newPwd;
  }
}
