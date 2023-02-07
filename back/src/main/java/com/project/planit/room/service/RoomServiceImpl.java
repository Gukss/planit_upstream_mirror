package com.project.planit.room.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
=======
import com.project.planit.common.exception.NotFoundException;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.room.dto.CreateRoomRequest;
import com.project.planit.room.dto.UpdateRoomRequest;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
<<<<<<< HEAD
import com.project.planit.util.BaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Optional;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

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
<<<<<<< HEAD
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정: baseEntity값을 토큰에서 가져오도록 설정
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
    @Transactional
    public Room createRoom(CreateRoomRequest request) {
<<<<<<< HEAD
        System.out.println(request.getStartDate()+" "+request.getStartDate().getClass().getName());
        System.out.println(request.getEndDate()+" "+request.getEndDate().getClass().getName());
        System.out.println("여기!!");
        //todo: tocken에서 memberAppId 가져다 사용하기
        String updater = "Gukss";
        String constructor = "Gukss";

        BaseRequest baseRequest = BaseRequest.builder()
            .updater(updater)
            .constructor(constructor)
            .build();

        Room newRoom = Room.create(request.getRoomName(), request.getStartDate(), request.getEndDate(), baseRequest);
=======
        Room newRoom = Room.builder()
                .roomName(request.getRoomName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .baseRequest(request.getBaseRequest())
                .build();
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
        return roomRepository.save(newRoom);
    }

    @Override
    @Transactional
    public Room updateRoom(UpdateRoomRequest request) {
        Room targetRoom = roomRepository.findById(request.getRoomId())
            .orElseThrow(
<<<<<<< HEAD
                ()-> new NotFoundExceptionMessage(NotFoundExceptionMessage.ROOM_NOT_FOUND)
=======
                ()-> new NotFoundException(NotFoundException.ROOM_NOT_FOUND)
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
            );
        targetRoom.changeName(request.getRoomName());
        targetRoom.changeDate(request.getStartDate(), request.getEndDate());
        return targetRoom;
    }
}
