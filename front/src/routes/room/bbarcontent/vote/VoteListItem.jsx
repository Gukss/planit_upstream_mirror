import React, { useRef, useState } from 'react';
import { useRecoilState } from 'recoil';
import classes from './VoteListItem.module.scss';

function VoteListItem(props) {
  const [isChecked, setIsChecked] = useState('');
  const [isVoted, setisVoted] = useState(false);

  function getChecked() {
    const isChecked = '';
    const inputNodeList = document.getElementsByName(props.vote.title);
    console.log(inputNodeList);

    inputNodeList.forEach(node => {
      if (node.checked) {
        console.log(node.value, '에 투표하겠다');
        setisVoted(true);
        const isChecked = node.value;
      }
    });
    alert('체크 안 골랐는데요?');
  }

  return (
    <div className={classes.item_box}>
      <div className={classes.item_box_title}>
        {isVoted ? (
          <i className='bx bxs-circle' style={{ color: '#1AD117' }}></i>
        ) : (
          <i className='bx bxs-circle' style={{ color: '#f94545' }}></i>
        )}
        <h3>{props.vote.title}</h3>
      </div>

      <div className={classes.item_box_content}>
        <fieldset>
          {props.vote.vote_item.map(it => {
            return (
              <label style={{ display: 'flex' }}>
                <input
                  type='radio'
                  value={it.vote_item_name}
                  name={props.vote.title}
                  style={{ width: '30px', height: '30px', border: '1px' }}
                  // ref={JSON.stringify(props.vote.title)}
                />
                <div>
                  <div
                    style={{ display: 'flex', justifyContent: 'space-between' }}
                  >
                    <p>{it.vote_item_name}</p>
                    <p>{it.count}명</p>
                  </div>
                  <div
                    style={{
                      width: '256px',
                      height: '10px',
                      backgroundColor: '#C6C6C6',
                    }}
                  ></div>
                </div>
              </label>
            );
          })}
        </fieldset>
      </div>

      <div className={classes.item_box_button}>
        {isVoted ? (
          <button disabled>투표 완료</button>
        ) : (
          <button onClick={getChecked}>투표하기</button>
        )}
      </div>
    </div>
  );
}

export default VoteListItem;
