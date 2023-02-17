import React from 'react';
import { useRecoilValue } from 'recoil';
import { chatMessages, userInfoState } from '../../../../app/store';

import classes from './ChatControl.module.scss';

function ChatControl({ publishMessage }) {
  const userInfo = useRecoilValue(userInfoState);

  // 제출한 메세지를 state에 담는 함수
  const submitMessage = e => {
    if (e.keyCode === 13) {
      e.preventDefault();

      publishMessage(e.target.value);

      e.target.value = '';
    }
  };
  // const submitMessageByButton = e => {
  //   console.log(input.value);

  //   e.preventDefault();

  //   // publishMessage(e.target.value);

  //   e.target.value = '';
  // };

  return (
    <div className={classes.chat_input_button}>
      <input
        type='text'
        placeholder='채팅을 입력해주세요'
        onKeyDown={submitMessage}
      />
      <button>
        <i className='bx bxs-paper-plane' style={{ color: '#ffffff' }}></i>
      </button>
    </div>
  );
}

export default ChatControl;
