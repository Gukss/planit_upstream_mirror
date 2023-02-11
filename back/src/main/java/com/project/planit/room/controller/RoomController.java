package com.project.planit.room.controller;

import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.member.entity.Member;
import com.project.planit.room.dto.*;
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomServiceImpl;
import java.net.URI;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.project.planit.room.controller
 * fileName       : RoomController
 * author         : Gukss
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR     NOTE
 * -----------------------------------------------------------
 * 2023-01-30        Gukss       최초생성
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/rooms")
public class RoomController {
  private final RoomServiceImpl roomService;

  private final JwtProvider jwtProvider;

  @PostMapping
  public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest request, @CookieValue String access) {
    //todo: token에서 id값으로 변환 => O
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Room newRoom = roomService.createRoom(request, Long.parseLong(claims.get("memberId").toString()));

    String newRoomName = newRoom.getRoomName();
    String requestRoomName = request.getRoomName();
    CreateRoomResponse createRoomResponse = CreateRoomResponse.create(newRoom.getId());

    URI uri = URI.create(""+newRoom.getId());
    ResponseEntity res = ResponseEntity.created(uri).body(createRoomResponse);
    return res;
  }

  @PatchMapping
  public ResponseEntity<UpdateRoomResponse> updateRoom(@RequestBody UpdateRoomRequest request, @CookieValue String access) {
    String parseToken = returnAccessToken(access);
    Claims claims = jwtProvider.parseClaims(parseToken);
    Long memberId = Long.parseLong(claims.get("memberId").toString());

    Room updatedRoom = roomService.updateRoom(request, memberId);
    UpdateRoomResponse updateRoomResponse = UpdateRoomResponse.create(updatedRoom.getId(),
        updatedRoom.getRoomName(), request.getStartDate(), request.getEndDate());

    ResponseEntity res = ResponseEntity.ok().body(updateRoomResponse);
    return res;
  }

  @GetMapping(path = "{roomId}")
  @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
  public ResponseEntity<ReadRoomResponse> readRoom(@PathVariable Long roomId){
    Room foundRoom = roomService.findById(roomId);
    //todo: 프론트랑 반환값 상의해서 넣어주기
    ReadRoomResponse readRoomResponse = ReadRoomResponse.create(foundRoom.getId(), foundRoom.getRoomName(), foundRoom.getStartDate(), foundRoom.getEndDate());
    return ResponseEntity.ok().body(readRoomResponse);
  }

  private String returnAccessToken(String fullToken){
    String parseToken = "";
    if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
      parseToken = fullToken.substring(7);
    }
    return parseToken;
  }
}
