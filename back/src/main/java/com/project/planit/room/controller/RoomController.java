package com.project.planit.room.controller;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.dto.CreateRoomResponse;
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomService;
import com.project.planit.vote.dto.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.project.planit.room.controller fileName       : RoomController author
 *  : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/rooms")
public class RoomController {
  private final RoomService roomService;
  @PostMapping
  public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest request){
    Room newRoom = roomService.createRoom(request);
    CreateRoomResponse createRoomResponse = CreateRoomResponse.create(newRoom.getId());
    return ResponseEntity.ok()
        .body(createRoomResponse);
  }
}
