import { useRecoilState } from 'recoil';
import React, { useState } from 'react';
// import Bbar from '../../../../common/bbar/Bbar';
import classes from './ChatControl.module.scss';
// import PlaceBox from './PlaceBox';
import { chatMessages } from '../../../../app/store';

function ChatControl() {
  // // 메시지의 내용에 들어가는 값 변수 value
  // const [messageValue, setMessageValue] = useState('');

  // // 제출한 메세지 전체를 담는 변수 message
  // const [message, setMessage] = useState('');

  // 전역 state인 chatMessages를 업데이트
  const [messages, setMessages] = useRecoilState(chatMessages);

  // 제출한 메세지를 state에 담는 함수
  const submitMessage = e => {
    if (e.keyCode === 13) {
      e.preventDefault();

      const newMessage = {
        message: e.target.value,
        constructor: '회원 정보',
      };
      console.log('지금 전역에 붙이려는 새 메시지', newMessage);
      setMessages([...messages, newMessage]);
      e.target.value = '';
      // console.log('전역 메세지 배열에 추가됐는지 확인', messages);
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
