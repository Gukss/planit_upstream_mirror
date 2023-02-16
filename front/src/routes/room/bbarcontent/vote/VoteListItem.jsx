import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { voteInformation, isVoted, voteFlag } from '../../../../app/store';
import classes from './VoteListItem.module.scss';

function VoteListItem(props) {
  const [voteCheck, setVoteCheck] = useState(false);
  const [isVote, setIsVote] = useRecoilState(isVoted);
  const [voteInfo, setVoteInfo] = useRecoilState(voteInformation);
  const [publishVoteFlag, setPublishVoteFlag] = useRecoilState(voteFlag);
  console.log('item', voteCheck, props.vote);

  // 투표 프로그래스바 너비 용 변수 voteCnt
  let voteCnt = 0;

  for (let i = 0; i < props.vote.votesListItem.length; i += 1) {
    voteCnt += props.vote.votesListItem[i].count;
  }

  // const voteCheck = isVote.filter(vote => {
  //   return vote.voteTitle === props.vote.title;
  // });

  // console.log('voteCnt', voteCnt, voteCheck[0].memberVote);

  // vote 관련 함수
  const getChecked = () => {
    const inputNodeList = document.getElementsByName(props.vote.title);
    console.log('chocie', inputNodeList);

    inputNodeList.forEach(node => {
      if (node.checked) {
        console.log(node.value, '에 투표하겠다');

        const newVoteInfo = props.vote.votesListItem.map(item =>
          item.name === node.value ? { ...item, count: item.count + 1 } : item
        );

        console.log('change', newVoteInfo);
        // setIsVote(
        //   isVote.map(item =>
        //     item.voteTitle === props.vote.title
        //       ? { ...item, memberVote: true }
        //       : item
        //   )
        // );
        setVoteCheck(prev => {
          return true;
        });
        setVoteInfo(
          voteInfo.map(voteList =>
            voteList.title === props.vote.title
              ? {
                  title: props.vote.title,
                  votesListItem: newVoteInfo,
                  // isVote: true,
                }
              : voteList
          )
        );
        setPublishVoteFlag([...publishVoteFlag, 1]);
      }
    });
  };

  return (
    <div className={classes.item_box}>
      <div className={classes.item_box_title}>
        {voteCheck ? (
          <i className='bx bxs-circle' style={{ color: '#1AD117' }}></i>
        ) : (
          <i className='bx bxs-circle' style={{ color: '#f94545' }}></i>
        )}
        <h3>{props.vote.title}</h3>
      </div>

      <div className={classes.item_box_content}>
        <fieldset style={{ border: 'none' }}>
          {props.vote.votesListItem.map(item => {
            return (
              <label>
                {voteCheck ? (
                  <input
                    disabled
                    type='radio'
                    value={item.name}
                    name={props.vote.title}
                    // ref={JSON.stringify(props.vote.title)}
                  />
                ) : (
                  <input
                    type='radio'
                    value={item.name}
                    name={props.vote.title}
                    // ref={JSON.stringify(props.vote.title)}
                  />
                )}
                <div className={classes.content_div}>
                  <div className={classes.content_namecount}>
                    <p>{item.name}</p>
                    <p>{item.count}명</p>
                  </div>
                  <div className={classes.content_progressbar}>
                    {voteCnt > 0 ? (
                      <div
                        className={classes.highlight}
                        style={{
                          width: `${(item.count / voteCnt) * 100}%`,
                        }}
                      ></div>
                    ) : null}
                  </div>
                </div>
              </label>
            );
          })}
        </fieldset>
      </div>
      <div className={classes.item_box_button}>
        {voteCheck ? (
          <button
            style={{
              backgroundColor: '#E6E9EF',
              color: '#777777',
              cursor: 'not-allowed',
            }}
          >
            투표 완료
          </button>
        ) : (
          <button onClick={getChecked}>투표하기</button>
        )}
      </div>
    </div>
  );
}

export default VoteListItem;
