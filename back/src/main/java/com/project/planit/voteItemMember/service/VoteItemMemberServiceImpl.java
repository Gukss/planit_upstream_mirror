package com.project.planit.voteItemMember.service;

<<<<<<< HEAD
import com.project.planit.common.exception.NotFoundExceptionMessage;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
=======
import com.project.planit.common.exception.NotFoundException;
import com.project.planit.member.entity.Member;
import com.project.planit.member.repository.MemberRepository;
import com.project.planit.util.BaseRequest;
import com.project.planit.vote.entity.Vote;
import com.project.planit.vote.repository.VoteRepository;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import com.project.planit.voteItem.entity.VoteItem;
import com.project.planit.voteItem.repository.VoteItemRepository;
import com.project.planit.voteItemMember.dto.CreateVoteItemMemberRequest;
import com.project.planit.voteItemMember.entity.VoteItemMember;
import com.project.planit.voteItemMember.repository.VoteItemMemberRepository;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
import javax.persistence.Id;
import javax.validation.constraints.Future;
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.project.planit.voteItemMember.service
 * fileName       : CreateVoteItemMemberRequestImpl
<<<<<<< HEAD
 * author         : Gukss
=======
 * author         : dongk
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 * date           : 2023-01-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
<<<<<<< HEAD
 * 2023-01-30        Gukss       최초 생성
 * 2023-01-30        Gukss       예외처리, 반환값 수정
 *
=======
 * 2023-01-30        dongk       최초 생성
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
 */
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class VoteItemMemberServiceImpl implements VoteItemMemberService {

    private final VoteItemMemberRepository voteItemMemberRepository;
    private final MemberRepository memberRepository;
    private final VoteItemRepository voteItemRepository;

    @Override
    @Transactional
<<<<<<< HEAD
    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request) {
        //todo: memberId는 토큰에서 받아온다. 변경하기
        Long memberId = request.getMemberId();
        //todo: BaseRequest 내용도 토큰에서 appId 받아오기
        String constructor = "Gukss";
        String updater = "Gukss";
        BaseRequest baseRequest = BaseRequest.builder()
            .constructor(constructor)
            .updater(updater)
            .build();
        Long voteItemId = request.getVoteItemId();

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.USER_NOT_FOUND));
        VoteItem voteItem = voteItemRepository.findById(voteItemId)
            .orElseThrow(() -> new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_LIST_NOT_FOUND));
=======
    //todo: memberId는 토큰에서 받아온다. 변경하기
    public VoteItemMember createVoteItemMember(CreateVoteItemMemberRequest request) {
        Long memberId = request.getMemberId();
        BaseRequest baseRequest = request.getBaseRequest();
        Long voteItemId = request.getVoteItemId();

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));
        VoteItem voteItem = voteItemRepository.findById(voteItemId)
            .orElseThrow(() -> new NotFoundException(NotFoundException.VOTE_ITEM_LIST_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1

        VoteItemMember newVoteItemMember = VoteItemMember.create(baseRequest, member, voteItem);
        return voteItemMemberRepository.save(newVoteItemMember);
    }

    @Override
    public List<VoteItemMember> findAllByVoteItemIdAndMemberId(Long voteItemId, Long MemberId) {
        VoteItem foundVoteItem = voteItemRepository.findById(voteItemId)
<<<<<<< HEAD
            .orElseThrow(() -> new NotFoundExceptionMessage(
                NotFoundExceptionMessage.VOTE_ITEM_NOT_FOUND));

        Member foundMember = memberRepository.findById(MemberId).orElseThrow(()->new NotFoundExceptionMessage(
            NotFoundExceptionMessage.USER_NOT_FOUND));

        return voteItemMemberRepository.findAllByVoteItemAndMember(foundVoteItem, foundMember)
            .orElseThrow(()->new NotFoundExceptionMessage(NotFoundExceptionMessage.VOTE_ITEM_MEMBER_LIST_NOT_FOUND));
=======
            .orElseThrow(() -> new NotFoundException(
                NotFoundException.VOTE_ITEM_NOT_FOUND));

        Member foundMember = memberRepository.findById(MemberId).orElseThrow(()->new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return voteItemMemberRepository.findAllByVoteItemAndMember(foundVoteItem, foundMember)
            .orElseThrow(()->new NotFoundException(NotFoundException.VOTE_ITEM_MEMBER_LIST_NOT_FOUND));
>>>>>>> 238216d15b44b9c3433b2f1723ab4d2689c983b1
    }
}
