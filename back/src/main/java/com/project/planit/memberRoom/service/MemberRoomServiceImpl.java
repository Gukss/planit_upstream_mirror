package com.project.planit.memberRoom.service;

import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.memberRoom.dto.createMemberRoomRequest;
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

        return memberRoomRepository.findAllByMember(member);
    }

    @Override
    public void updateMemberRoom() {

    }

    @Override
    @Transactional
    public void createMemberRoom(createMemberRoomRequest request) {
        Room room=roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        // @TODO : 토큰에 어떤 값을 넣을지에 따라 바뀜
        Member member=memberRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        memberRoomRepository.save(MemberRoom.create(request,room,member));

    }
}
