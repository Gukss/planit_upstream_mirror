package com.project.planit.memberRoom.service;

import com.project.planit.common.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        Room room=roomRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return memberRoomRepository.findAllByMemberAndRoom(member,room);
    }

    @Override
    @Transactional
    public void updateMemberRoom(UpdateMemberRoomRequest request) {
        MemberRoom memberRoom=memberRoomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        memberRoom.update(memberRoom.getMember().getAppId(),request);
    }

    @Override
    @Transactional
    public void createMemberRoom(CreateMemberRoomRequest request) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // @TODO : 토큰에 어떤 값을 넣을지에 따라 바뀜
        Member member=memberRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));
        memberRoomRepository.save(MemberRoom.create(request,room,member));

    }
}
