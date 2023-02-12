import { useRecoilState } from 'recoil';
<<<<<<< HEAD
import React, { useEffect, useRef, useState } from 'react';
=======
import React, { useEffect, useRef } from 'react';
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb

import * as StompJs from '@stomp/stompjs';
import SockJS from 'sockjs-client';

<<<<<<< HEAD
// import Bbar from '../../../../common/bbar/Bbar';
=======
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
import classes from './ChatControl.module.scss';
import { chatMessages } from '../../../../app/store';

function ChatControl() {
  const [messages, setMessages] = useRecoilState(chatMessages);
  const client = useRef({});

<<<<<<< HEAD
  const subscribe = () => {
    client.current.subscribe('/sub/room/1', ({ body }) => {
=======
  // 여기 1 나중에 룸번호로 변경해야함
  const subscribe = async () => {
    await client.current.subscribe('/sub/room/1', ({ body }) => {
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
      setMessages(messages => [...messages, JSON.parse(body)]);
    });
  };

<<<<<<< HEAD
  const publish = message => {
=======
  const publish = async message => {
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
    if (!client.current.connected) {
      return;
    }

<<<<<<< HEAD
    client.current.publish({
      destination: '/pub/message',
      body: JSON.stringify({ roomId: 1, message }),
    });
  };
=======
    await client.current.publish({
      destination: '/pub/message',
      // 여기 1 나중에 룸번호로 변경해야함
      body: JSON.stringify({ roomId: 1, message }),
    });
  };

  const connect = () => {
    client.current = new StompJs.Client({
      // brokerURL: "ws://localhost:8080/ws-stomp/websocket", // 웹소켓 서버로 직접 접속
      webSocketFactory: () =>
        new SockJS('https://i8b202.p.ssafy.io/api/ws-stomp'), // proxy를 통한 접속
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
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb

  // 제출한 메세지를 state에 담는 함수
  const submitMessage = e => {
    if (e.keyCode === 13) {
      e.preventDefault();

      publish(e.target.value);

      e.target.value = '';
    }
  };

<<<<<<< HEAD
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

=======
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
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
