package com.project.planit.chatting.service;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.entity.ChattingMessage;
import com.project.planit.chatting.repository.ChattingRepository;
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ChattingServiceImpl implements ChattingService {
    private final ChattingRepository chattingRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ChattingMessage> findChattingMessage(Long memberId,Long roomId) {
        Room room=roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return chattingRepository.findAllByMemberAndRoom(member,room);
    }

    @Override
    @Transactional
    public void createChattingMessage(CreateChattingRequest request,Long memberId) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        ChattingMessage chattingMessage=ChattingMessage.create(request,room,member);

        chattingRepository.save(chattingMessage);
    }
}
