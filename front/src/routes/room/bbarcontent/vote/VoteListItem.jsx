import React from 'react';
import { useRecoilState } from 'recoil';
import { voteInformation } from '../../../../app/store';
import classes from './VoteListItem.module.scss';

function VoteListItem(props) {
  const [voteInfo, setVoteInfo] = useRecoilState(voteInformation);
  console.log('item', props.vote);

  const getChecked = () => {
    const inputNodeList = document.getElementsByName(props.vote.title);
    console.log('chocie', inputNodeList);

    inputNodeList.forEach(node => {
      if (node.checked) {
        console.log(node.value, '에 투표하겠다');

        const newVoteInfo = props.vote.voteItem.map(item =>
          item.voteItem === node.value
            ? { ...item, count: item.count + 1 }
            : item
        );

        console.log('change', newVoteInfo);
        setVoteInfo(
          voteInfo.map(voteList =>
            voteList.title === props.vote.title
              ? {
                  title: props.vote.title,
                  voteItem: newVoteInfo,
                  isVote: true,
                }
              : voteList
          )
        );
      }
    });
  };

  return (
    <div className={classes.item_box}>
      <div className={classes.item_box_title}>
        {props.vote.isVote ? (
        
          <i className='bx bxs-circle' style={{ color: '#1AD117' }}></i>
        ) : (
          <i className='bx bxs-circle' style={{ color: '#f94545' }}></i>
        )}
        <h3>{props.vote.title}</h3>
      </div>

      <div className={classes.item_box_content}>
        <fieldset style={{ border: 'none' }}>
          {props.vote.voteItem.map(item => {
            return (
              <label>
                {props.vote.isVote ? (
                  <input
                    disabled
                    type='radio'
                    value={item.voteItem}
                    name={props.vote.title}
                    // ref={JSON.stringify(props.vote.title)}
                  />
                ) : (
                  <input
                    type='radio'
                    value={item.voteItem}
                    name={props.vote.title}
                    // ref={JSON.stringify(props.vote.title)}
                  />
                )}
                <div className={classes.content_div}>
                  <div className={classes.content_namecount}>
                    <p>{item.voteItem}</p>
                    <p>{item.count}명</p>
                  </div>
                  <div className={classes.content_progressbar}></div>
                </div>
              </label>
            );
          })}
        </fieldset>
      </div>
      <div className={classes.item_box_button}>
        {props.vote.isVote ? (
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
