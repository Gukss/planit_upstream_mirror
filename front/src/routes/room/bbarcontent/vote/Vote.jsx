import { useRecoilState } from 'recoil';
import React, { useState } from 'react';
import Swal from 'sweetalert2';
import { voteInformation, voteFlag, isVoted } from '../../../../app/store';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Vote.module.scss';
import VoteList from './VoteList';

function Vote() {
  // const [voteInfo, setVoteInfo] = useState([]);
  const [isVote, setIsVote] = useRecoilState(isVoted);
  const [voteInfo, setVoteInfo] = useRecoilState(voteInformation);
  const [publishVoteFlag, setPublishVoteFlag] = useRecoilState(voteFlag);
  // 투표 생성 모달
  const onCreateVote = () => {
    const { value: getVoteInfo } = Swal.fire({
      html: `<div>
              <input id="vote-title" style="width: 80%; background-color: #F5F5F5" class="swal2-input" placeholder="투표 제목을 입력하세요" autofocus />
              <div id="inputs">
                <input id="input1" style="width: 80%" class="swal2-input" placeholder="항목 입력">
                <input id="input2" style="width: 80%" class="swal2-input" placeholder="항목 입력">
                <input id="input3" style="width: 80%" class="swal2-input" placeholder="항목 입력">
                <input id="input4" style="width: 80%" class="swal2-input" placeholder="항목 입력">
                <input id="input5" style="width: 80%" class="swal2-input" placeholder="항목 입력">
              </div>              
            </div>`,
      showCancelButton: true,
      confirmButtonColor: '#2B3F6B',
      title: '🚀 투표 만들기',
      // input: 'text',
      // inputLabel: '정하기 어려울 땐 다수결이지!',
      // inputPlaceholder: '투표 제목을 입력하세요',
      didOpen: () => {
        document.getElementById('vote-title').focus();
      },
      preConfirm: () => {
        return {
          title: document.getElementById('vote-title').value,
          voteItem: [
            document.getElementById('input1').value,
            document.getElementById('input2').value,
            document.getElementById('input3').value,
            document.getElementById('input4').value,
            document.getElementById('input5').value,
          ],
        };
      },
    }).then(info => {
      if (info.isConfirmed) {
        // const json = JSON.stringify(value.value);
        // console.log('최종', info.value);
        const voteArray = info.value.voteItem;
        const voteItems = [];

        for (let i = 0; i < voteArray.length; i += 1) {
          if (voteArray[i] !== '') {
            voteItems.push(voteArray[i]);
          }
        }

        const voteInfos = [];
        for (let i = 0; i < voteItems.length; i += 1) {
          voteInfos.push({
            name: voteItems[i],
            count: 0,
          });
        }

        setVoteInfo([
          ...voteInfo,
          {
            title: info.value.title,
            votesListItem: voteInfos,
            // isVote: false,
          },
        ]);

        setIsVote([
          ...isVote,
          { voteTitle: info.value.title, memberVote: false },
        ]);

        setPublishVoteFlag([...publishVoteFlag, 1]);
      }
    });
  };
  console.log('투표정보', voteInfo);

  return (
    <Bbar>
      <div className={classes.vote_title}>
        <div className={classes.title_title}>투표</div>
        <p>
          여행 일정을 짜는 중 의견이 정해지지 않을 땐 투표를 만들어
          진행해보세요.
        </p>
        <br />
      </div>
      <div className={classes.vote_body}>
        <div className={classes.vote_body_head}>
          <p>투표 내역</p>
          <div
            onClick={onCreateVote}
            onKeyDown={onCreateVote}
            role='button'
            tabIndex={0}
            style={{ cursor: 'pointer' }}
          >
            <i className='bx bx-plus'></i>
          </div>
        </div>
        <hr />
        <VoteList voteInfo={voteInfo} />
      </div>
    </Bbar>
  );
}

export default Vote;
