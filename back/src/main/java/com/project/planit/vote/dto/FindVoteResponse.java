package com.project.planit.vote.dto;

import com.project.planit.vote.entity.Vote;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.project.planit.vote.dto
 * fileName       : FindVoteByRoomIdResponse
 * author         : Gukss
 * date           : 2023-01-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-29        Gukss       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FindVoteResponse {
    //반환을 위한 dto(객체)를 하나 만들어서 ArrayList로 만들어서 반환한다.
//    @Builder
//    public class ListItem{
        private Long voteId;
        private String title;

        //FindVoteResponse 객체 안에서만 사용할 것이기 때문에 static안해도 된다.
//        public ListItem create(Long voteId, String title){
//            return ListItem.builder()
//                .voteId(voteId)
//                .title(title)
//                .build();
//        }
//    }
//    List<ListItem> foundVoteInfoArr = new ArrayList<>();

    public static FindVoteResponse create(List<Vote> foundVotes) {


//        for(Vote vote: foundVotes){
//            Long voteId = vote.getId();
//            String title = vote.getTitle();
//            ListItem listItem = ListItem.builder()
//                .voteId(voteId)
//                .title(title)
//                .build();
//            foundVoteInfoArr.add(listItem);
//        }
//        return FindVoteResponse.builder()
//                .foundVoteInfoList(foundVotes)
//                .build();
    }
}
