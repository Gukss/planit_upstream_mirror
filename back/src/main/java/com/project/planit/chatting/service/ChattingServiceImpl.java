package com.project.planit.chatting.service;

import com.project.planit.chatting.dto.CreateChattingRequest;
import com.project.planit.chatting.entity.ChattingMessage;
import com.project.planit.chatting.repository.ChattingRepository;
<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
=======
import com.project.planit.common.exception.NotFoundException;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
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
<<<<<<< HEAD
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));
=======
                .orElseThrow(() -> new NotFoundException(NotFoundException.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

        return chattingRepository.findAllByMemberAndRoom(member,room);
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public ChattingMessage createChattingMessage(CreateChattingRequest request,Long memberId) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        ChattingMessage chattingMessage=ChattingMessage.create(request,room,member);

        return chattingRepository.save(chattingMessage);
=======
    public void createChattingMessage(CreateChattingRequest request,Long memberId) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.ROOM_NOT_FOUND));

        Member member= memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        ChattingMessage chattingMessage=ChattingMessage.create(request,room,member);

        chattingRepository.save(chattingMessage);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }
}
