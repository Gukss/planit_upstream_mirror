import React from 'react';
import { useRecoilValue } from 'recoil';
import { chatMessages, userInfoState } from '../../../../app/store';

import classes from './ChatControl.module.scss';

function ChatControl({ publishMessage }) {
  const userInfo = useRecoilValue(userInfoState);

  // 제출한 메세지를 state에 담는 함수
  const submitMessage = e => {
    console.log('submitMessage');
    if (e.keyCode === 13) {
      e.preventDefault();

      publishMessage(e.target.value);

      e.target.value = '';
    }
  };

  return (
    <div className={classes.chat_input_button}>
      <input
        type='text'
        placeholder='채팅을 입력해주세요'
        onKeyDown={submitMessage}
      />
      <button>보내기</button>
    </div>
  );
}

export default ChatControl;
