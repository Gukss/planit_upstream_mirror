package com.project.planit.vote.entity;

import com.project.planit.room.entity.Room;
import com.project.planit.util.BaseEntity;
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
 */
@Entity
@Getter
@Table(name="vote")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Vote extends BaseEntity{

    @Id @GeneratedValue
    @Column(name="vote_id")
    private Long id;

    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @NotNull
    private Room room;

    @Embedded
    @NotNull
    private BaseRequest baseRequest;

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
}
