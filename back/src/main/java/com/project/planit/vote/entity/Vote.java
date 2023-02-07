package com.project.planit.vote.entity;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
<<<<<<< HEAD
import com.project.planit.vote.dto.FindVoteResponse;
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import javax.validation.constraints.NotNull;

import com.project.planit.util.BaseRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import lombok.NoArgsConstructor;


/**
 * packageName    : com.project.planit.vote.entity
 * fileName       : Vote
 * author         : Gukss
 * date           : 2023-01-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2023-01-23        dongk       최초생성
<<<<<<< HEAD
 * 2023-02-01        Gukss       REST API 문서에 맞게 수정, List로 반환위한 dto관련 설정
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 */
@Entity
@Getter
@Table(name="vote")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Vote extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id")
    private Long id;

    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    @NotNull
    private Room room;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

<<<<<<< HEAD
    //==== 생성 메서드 ====
    public static Vote create(String title, Room room, BaseRequest baseRequest){
        return Vote.builder()
            .title(title)
            .room(room)
            .baseRequest(baseRequest)
            .build();
    }

=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    /**
     * Change title.
     * methodName : changeTitle
     * author : Gukss
     * description : 투표 이름을 바꾸는 메소드
     *
     * @param newTitle the new title
     */
    public void changeTitle(String newTitle){
        //바꾼 이름이 비어있을 때 untitle로 변경
        if(newTitle.equals("")){
            newTitle = "untitle";
        }
        this.title = newTitle;
    }
<<<<<<< HEAD

    public FindVoteResponse createFindVoteResponse(){
        return FindVoteResponse.builder()
            .voteId(this.id)
            .title(this.title)
            .build();
    }
=======
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
}
