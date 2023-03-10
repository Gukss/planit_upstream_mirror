package com.project.planit.storage.controller;


import com.project.planit.chatting.dto.CreateChattingMessageRequest;
import com.project.planit.common.auth.jwt.JwtProvider;
import com.project.planit.member.entity.Member;
import com.project.planit.room.dto.UpdateRoomResponse;
import com.project.planit.room.entity.Room;
import com.project.planit.storage.dto.*;
import com.project.planit.storage.entity.Category;
import com.project.planit.storage.entity.Storage;
import com.project.planit.storage.service.StorageServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storages")
public class StorageController {
    private final StorageServiceImpl storageService;
    private final JwtProvider jwtProvider;

    private final SimpMessageSendingOperations messagingTemplate;

    // pub, sub관리 컨트롤러 RequestMapping 무시..
    @MessageMapping("/markers")
    public void message(SocketStorageRequest socketStorageRequest){
        messagingTemplate.convertAndSend("/sub/markers/" + socketStorageRequest.getRoomId(), socketStorageRequest);
    }

    @GetMapping(path="/rooms/{roomId}")
    public ResponseEntity<?> findStorageList(@PathVariable Long roomId, @RequestHeader("Authorization") String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long reqestMemberId = Long.parseLong(claims.get("memberId").toString());

        return ResponseEntity.ok(storageService.findStorageList(roomId, reqestMemberId));
    }

    @PostMapping
    public ResponseEntity<CreateStorageResponse> createStorage(@RequestBody CreateStorageRequest request, @RequestHeader("Authorization") String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long reqestMemberId = Long.parseLong(claims.get("memberId").toString());

        Storage newStorage = storageService.createStorage(request, reqestMemberId);
        String name = newStorage.getStorageName();
        Long storagesId = newStorage.getId();
        double lat = newStorage.getLat();
        double lng = newStorage.getLng();
        String dayOrder = newStorage.getDayOrder();
        Long indexOrder = newStorage.getIndexOrder();
        Category categoryName = newStorage.getCategoryName();
        Long roomId = newStorage.getRoom().getId();
        Long memberId = newStorage.getMember().getId();
        Boolean confirmed = newStorage.getConfirmed();
        CreateStorageResponse createStorageResponse = CreateStorageResponse.create(memberId, storagesId, name, confirmed, lat, lng, dayOrder,indexOrder, categoryName, roomId);

        ResponseEntity res = ResponseEntity.ok().body(createStorageResponse);
        return res;
    }

    @PatchMapping
    public ResponseEntity<UpdateStorageResponse> updateStorage(@RequestBody UpdateStorageRequest request, @RequestHeader("Authorization") String access) {
        String parseToken = returnAccessToken(access);
        Claims claims = jwtProvider.parseClaims(parseToken);
        Long memberId = Long.parseLong(claims.get("memberId").toString());

        Storage updatedStorage = storageService.updateStorage(request, memberId);
        Member member = updatedStorage.getMember();
        Room room = updatedStorage.getRoom();
        UpdateStorageResponse updateStorageResponse = UpdateStorageResponse.create(member.getId(), updatedStorage.getId(), updatedStorage.getStorageName(), updatedStorage.getConfirmed(), updatedStorage.getLat(), updatedStorage.getLng(), updatedStorage.getDayOrder(), updatedStorage.getIndexOrder(), updatedStorage.getCategoryName(), room.getId());
        ResponseEntity res = ResponseEntity.ok().body(updateStorageResponse);
        return res;
    }

    private String returnAccessToken(String fullToken) {
        String parseToken = "";
        if (StringUtils.hasText(fullToken) && fullToken.startsWith("Bearer")) {
            parseToken = fullToken.substring(7);
        }
        return parseToken;
    }

}
