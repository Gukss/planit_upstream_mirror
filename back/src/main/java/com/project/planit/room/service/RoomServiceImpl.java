package com.project.planit.room.service;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

/**
 * packageName    : com.project.planit.room.service
 * fileName       : RoomServiceImpl
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public Room createRoom(@RequestBody CreateRoomRequest request) {
        Room newRoom = Room.builder()
                .roomName("새로 생성한 방")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .baseRequest(request.getBaseRequest())
                .build();
        return roomRepository.save(newRoom);
    }
}
