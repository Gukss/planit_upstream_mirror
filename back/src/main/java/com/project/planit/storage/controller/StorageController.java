package com.project.planit.storage.controller;


import com.project.planit.storage.dto.CreateStorageRequest;
import com.project.planit.storage.dto.UpdateStorageRequest;
import com.project.planit.storage.service.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storages")
public class StorageController {
    private final StorageServiceImpl storageService;
    @GetMapping(path="/rooms/{roomId}")
    public ResponseEntity<?> findStorageList(@PathVariable Long roomId){
     return ResponseEntity.ok(storageService.findStorageList(roomId));
    }

    @PostMapping
    public ResponseEntity<?> createStorage(@RequestBody CreateStorageRequest request){
        // @TODO : 토큰 아이디로 변환
        Long id=1L;
        storageService.createStorage(request,id);
        return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<?> updateStorage(@RequestBody UpdateStorageRequest request){
        // @TODO : 토큰 아이디로 변환
        Long id=1L;
        storageService.updateStorage(request,id);
        return ResponseEntity.ok("");
    }
}
