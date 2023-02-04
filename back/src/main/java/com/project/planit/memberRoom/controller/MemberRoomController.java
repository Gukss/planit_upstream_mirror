package com.project.planit.memberRoom.controller;

import com.project.planit.member.entity.Member;
import com.project.planit.memberRoom.dto.*;
import com.project.planit.memberRoom.entity.MemberRoom;
import com.project.planit.memberRoom.service.MemberRoomServiceImpl;
import com.project.planit.room.entity.Room;
import com.project.planit.voteItem.dto.FindVoteItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.project.planit.memberRoom.controller fileName       : MemberRoomController
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/users")
public class MemberRoomController {
    private final MemberRoomServiceImpl memberRoomService;

    @GetMapping
    public ResponseEntity<List<FindMemberRoomResponse>> findMemberRoom(){
        Long id=1L; // @TODO : 나중에 토큰 아이디로 변환
        List<MemberRoom> foundMemberRoom = memberRoomService.findMemberRoom(id);

        List<FindMemberRoomResponse> resList = new ArrayList<>();

        for(MemberRoom memberRoom : foundMemberRoom){
            resList.add(memberRoom.createFindMemberRoomResponse());
        }
        ResponseEntity<List<FindMemberRoomResponse>> res = ResponseEntity.ok().body(resList);
        return res;
    }

    @PostMapping
    public ResponseEntity<CreateMemberRoomResponse> createMemberRoom(@RequestBody CreateMemberRoomRequest request){
        MemberRoom memberRoom = memberRoomService.createMemberRoom(request);
        Long roomId = memberRoom.getRoom().getId();
        CreateMemberRoomResponse createMemberRoomResponse = CreateMemberRoomResponse.builder()
                .roomId(roomId)
                .build();
        ResponseEntity<CreateMemberRoomResponse> res = ResponseEntity.ok().body(createMemberRoomResponse);
        return res;
    }

    @PatchMapping
    public ResponseEntity<UpdateMemberRoomResponse> updateMemberRoom(@RequestBody UpdateMemberRoomRequest request){
        MemberRoom memberRoom = memberRoomService.updateMemberRoom(request);

        Room room = memberRoom.getRoom();
        Member member = memberRoom.getMember();

        UpdateMemberRoomResponse updateMemberRoomResponse = UpdateMemberRoomResponse.builder()
                .roomId(room.getId())
                .participation(memberRoom.getParticipation())
                .memberId(member.getId())
                .build();
        return ResponseEntity.ok().body(updateMemberRoomResponse);
    }
}
