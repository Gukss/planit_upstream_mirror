package com.project.planit.chatting.service;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.entity.ChattingMessage;

import java.util.List;

public interface ChattingService {
    List<ChattingMessage> findChattingMessage(Long memberId,Long roomId);
    void createChattingMessage(CreateChattingRequest request,Long memberId);
}
