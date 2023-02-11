package com.project.planit.room.service;

import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.dto.UpdateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.dto.UpdateVoteRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * packageName    : com.project.planit.room.service
 * fileName       : RoomServiceTest
 * author         : dongk
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        dongk       최초 생성
 */
@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("방생성")
    //@Rollback(false)
    void 방생성() throws Exception {
        //given
        Room newRoom = makeNewRoom();
        //then
        em.flush();
        //만든 투표와 조회한 투표가 같아야한다.
        assertEquals(newRoom, roomRepository.findById(newRoom.getId()).get());
    }

    @Test
    @DisplayName("방조회")
    //@Rollback(false)
    void 방조회() throws Exception {
        //given
        Room newRoom = makeNewRoom();
        //when
        Room foundRoom = roomService.findById(newRoom.getId());
        //then
        em.flush();
        assertEquals(roomRepository.findById(newRoom.getId()).get().getId(), foundRoom.getId());
    }

    @Test
    @DisplayName("방정보변경")
    //@Rollback(false)
    void 방정보변경() throws Exception {
        //given
        Room newRoom = makeNewRoom();
        //when
        //방 정보 변경을 위한 updateRequest 만들기
        UpdateRoomRequest updateRequest = UpdateRoomRequest.builder()
                .roomId(newRoom.getId())
                .baseRequest(BaseRequest.builder()
                        .constructor("Gukss")
                        .updater("Gukss")
                        .build())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .roomName("수정된 방 이름")
                .build();
        Room updatedRoom = roomService.updateRoom(updateRequest, 1L);
        //then
        em.flush();
        assertEquals(updatedRoom.getRoomName(), newRoom.getRoomName());
    }

    Room makeNewRoom(){
        //request 만들기
        CreateRoomRequest roomRequest = CreateRoomRequest.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .roomName("새로운 방 이름")
                .baseRequest(BaseRequest.builder()
                        .constructor("Gukss")
                        .updater("Gukss")
                        .build())
                .build();
        //만든 request로 방 만들기
        return roomService.createRoom(roomRequest, 1L);
    }
}
