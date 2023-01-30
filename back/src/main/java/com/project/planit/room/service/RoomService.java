package com.project.planit.room.service;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.dto.UpdateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.vote.dto.UpdateVoteRequest;

import java.util.Optional;

/**
 * packageName    : com.project.planit.room.service
 * fileName       : RoomService
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
public interface RoomService {
    public Room findById(Long id);
    public Room createRoom(CreateRoomRequest request);
    public Room updateRoom(UpdateRoomRequest request);

}
