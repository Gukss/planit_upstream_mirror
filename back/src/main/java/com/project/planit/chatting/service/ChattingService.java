package com.project.planit.chatting.service;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.entity.ChattingMessage;

import java.util.List;

public interface ChattingService {
    List<ChattingMessage> findChattingMessage(Long memberId,Long roomId);
<<<<<<< HEAD
    ChattingMessage createChattingMessage(CreateChattingRequest request,Long memberId);
=======
    void createChattingMessage(CreateChattingRequest request,Long memberId);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
