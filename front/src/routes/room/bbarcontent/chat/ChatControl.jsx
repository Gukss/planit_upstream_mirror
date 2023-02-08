import { useRecoilState } from 'recoil';
import React, { useEffect, useRef, useState } from 'react';

import * as StompJs from '@stomp/stompjs';
import SockJS from 'sockjs-client';

// import Bbar from '../../../../common/bbar/Bbar';
import classes from './ChatControl.module.scss';
// import PlaceBox from './PlaceBox';
import { chatMessages } from '../../../../app/store';

function ChatControl() {
  const [messages, setMessages] = useRecoilState(chatMessages);
  const client = useRef({});

  const subscribe = () => {
    client.current.subscribe('/sub/room/1', ({ body }) => {
      setMessages(messages => [...messages, JSON.parse(body)]);
    });
  };

  const publish = message => {
    if (!client.current.connected) {
      return;
    }

    client.current.publish({
      destination: '/pub/message',
      body: JSON.stringify({ roomId: 1, message }),
    });
  };

  // 제출한 메세지를 state에 담는 함수
  const submitMessage = e => {
    if (e.keyCode === 13) {
      e.preventDefault();

      publish(e.target.value);

      e.target.value = '';
    }
  };

  const connect = () => {
    client.current = new StompJs.Client({
      // brokerURL: "ws://localhost:8080/ws-stomp/websocket", // 웹소켓 서버로 직접 접속
      webSocketFactory: () => new SockJS('http://localhost:8080/ws-stomp'), // proxy를 통한 접속
      connectHeaders: {},
      debug: str => {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        console.log('커넥트 되는 시점');
        subscribe();
      },
      onStompError: frame => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  useEffect(() => {
    connect();
    return () => disconnect();
  }, []);
  console.log(messages);

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
