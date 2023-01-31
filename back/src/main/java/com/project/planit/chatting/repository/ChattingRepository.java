package com.project.planit.chatting.repository;

import com.project.planit.chatting.entity.ChattingMessage;
import com.project.planit.member.entity.Member;
import com.project.planit.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRepository extends JpaRepository<ChattingMessage,Long> {
    List<ChattingMessage> findAllByMemberAndRoom(Member member, Room room);
}
