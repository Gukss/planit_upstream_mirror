package com.project.planit.memberRoom.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.common.exception.NotFoundMemberRoomException;
=======
import com.project.planit.common.exception.NotFoundException;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.memberRoom.dto.CreateMemberRoomRequest;
import com.project.planit.memberRoom.dto.UpdateMemberRoomRequest;
import com.project.planit.memberRoom.entity.MemberRoom;
import com.project.planit.memberRoom.repository.MemberRoomRepository;
import com.project.planit.room.entity.Room;
import com.project.planit.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * packageName    : com.project.planit.memberRoom.service fileName       : MemberRoomServiceImpl
 * author         : SSAFY date           : 2023-01-30 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2023-01-30        SSAFY       최초
 * 생성
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberRoomServiceImpl implements MemberRoomService{
    private final MemberRoomRepository memberRoomRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<MemberRoom> findMemberRoom(Long id) {
        Member member = memberRepository.findById(id)
<<<<<<< HEAD
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        Room room=roomRepository.findById(id)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        return memberRoomRepository.findAllByMemberAndRoom(member,room).orElseThrow(()->new NotFoundMemberRoomException());

=======
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Room room=roomRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return memberRoomRepository.findAllByMemberAndRoom(member,room);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public MemberRoom updateMemberRoom(UpdateMemberRoomRequest request) {
        MemberRoom memberRoom=memberRoomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        memberRoom.update(memberRoom.getMember().getAppId(),request);
        return memberRoom;
=======
    public void updateMemberRoom(UpdateMemberRoomRequest request) {
        MemberRoom memberRoom=memberRoomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        memberRoom.update(memberRoom.getMember().getAppId(),request);
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public MemberRoom createMemberRoom(CreateMemberRoomRequest request) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));

        // @TODO : 토큰에 어떤 값을 넣을지에 따라 바뀜
        Long memberId = 1L;
        Member member=memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));
        MemberRoom savedMemberRoom = memberRoomRepository.save(MemberRoom.create(request, room, member));
        return savedMemberRoom;
=======
    public void createMemberRoom(CreateMemberRoomRequest request) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // @TODO : 토큰에 어떤 값을 넣을지에 따라 바뀜
        Member member=memberRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));
        memberRoomRepository.save(MemberRoom.create(request,room,member));

>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }
}
