package com.project.planit.memberRoom.controller;

import com.project.planit.memberRoom.dto.createMemberRoomRequest;
import com.project.planit.memberRoom.dto.updateMemberRoomRequest;
import com.project.planit.memberRoom.service.MemberRoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findMemberRoom(){
        Long id=1L; // @TODO : 나중에 토큰 아이디로 변환
        return ResponseEntity.ok(memberRoomService.findMemberRoom(id));
    }

    @PostMapping
    public ResponseEntity<?> createMemberRoom(@RequestBody createMemberRoomRequest request){
        memberRoomService.createMemberRoom(request);
         return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<?> updateMemberRoom(@RequestBody updateMemberRoomRequest request){
        memberRoomService.updateMemberRoom(request);
        return ResponseEntity.ok("ok");
    }
}
