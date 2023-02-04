package com.project.planit.room.controller;

import com.project.planit.room.dto.*;
import com.project.planit.room.entity.Room;
import com.project.planit.room.service.RoomService;
import com.project.planit.vote.dto.CreateVoteResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  private final RoomService roomService;
  @PostMapping
  public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest request){
    Room newRoom = roomService.createRoom(request);

    String newRoomName = newRoom.getRoomName();
    String requestRoomName = request.getRoomName();
    CreateRoomResponse createRoomResponse = CreateRoomResponse.create(newRoom.getId());

    URI uri = URI.create(""+newRoom.getId());
    ResponseEntity res = ResponseEntity.created(uri).body(createRoomResponse);
    return res;
  }

  @PatchMapping
  public ResponseEntity<UpdateRoomResponse> updateRoom(@RequestBody UpdateRoomRequest request){
    Room updatedRoom = roomService.updateRoom(request);
    UpdateRoomResponse updateRoomResponse = UpdateRoomResponse.create(updatedRoom.getId(),
        updatedRoom.getRoomName(), request.getStartDate(), request.getEndDate());

    Long updatedRoomId = updatedRoom.getId();
    Long reqestRoomId = request.getRoomId();

    ResponseEntity res = ResponseEntity.ok().body(updateRoomResponse);
    return res;
  }

  @GetMapping(path = "{roomId}")
  public ResponseEntity<ReadRoomResponse> readRoom(@PathVariable Long roomId){
    Room foundRoom = roomService.findById(roomId);
    //todo: 프론트랑 반환값 상의해서 넣어주기
    ReadRoomResponse readRoomResponse = ReadRoomResponse.create(foundRoom.getId(), foundRoom.getRoomName(), foundRoom.getStartDate(), foundRoom.getEndDate());
    return ResponseEntity.ok().body(readRoomResponse);
  }
}
