/* eslint-disable eqeqeq */
import 'boxicons/css/boxicons.min.css';
import { useEffect, useRef } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { Routes, Route, Outlet } from 'react-router-dom';

import * as StompJs from '@stomp/stompjs';
import SockJS from 'sockjs-client';

import RoomLayout from './RoomLayout';
import Search from './bbarcontent/search/Search';
import Place from './bbarcontent/place/Place';
import Schedule from './bbarcontent/schedule/Schedule';
import Vote from './bbarcontent/vote/Vote';
import Chat from './bbarcontent/chat/Chat';
import {
  chatMessages,
  stompClient,
  userMarkers,
  socketMarkers,
  userInfoState,
} from '../../app/store';

function Room() {
  const client = useRef({});
  const [messages, setMessages] = useRecoilState(chatMessages);
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const [socketMarker, setSocketMarker] = useRecoilState(socketMarkers);
  const userInfo = useRecoilValue(userInfoState);

  console.log('여기는 룸 입니다.');

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeChatting = async () => {
    console.log('채팅');
    await client.current.subscribe('/sub/room/1', ({ body }) => {
      console.log(body);
      setMessages(pre => [...pre, JSON.parse(body)]);
    });
  };

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeMarkers = async () => {
    console.log('마커 subscribe');
    await client.current.subscribe('/sub/markers/1', ({ body }) => {
      setSocketMarker(JSON.parse(body).storageItemList);
      console.log('마커', body);
      console.log('파스 리스트', JSON.parse(body).storageItemList);
    });
  };

  const stompActive = async () => {
    await client.current.activate();
  };

  const connect = async () => {
    client.current = new StompJs.Client({
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
        subscribeChatting();
        subscribeMarkers();
      },
      onStompError: frame => {
        console.error(frame);
      },
    });
    console.log('connect');
    await stompActive();
  };

  const disconnect = () => {
    console.log('disconnect');
    client.current.deactivate();
  };

  useEffect(() => {
    console.log('방 처음');
    connect();
    return () => disconnect();
  }, []);

  const publishMarker = async markers => {
    console.log('publish marker');
    if (!client.current.connected) {
      return;
    }
    console.log('publish markers info: ', markers);
    await client.current.publish({
      destination: '/pub/markers',
      // 여기 1 나중에 룸번호로 변경해야함
      body: JSON.stringify({ roomId: 1, storageItemList: [...markers] }),
      // body: { roomId: 1, markers },
    });
  };

  const publishMessage = async message => {
    console.log('publish messages');
    console.log(messages);
    if (!client.current.connected) {
      return;
    }
    await client.current.publish({
      destination: '/pub/message',
      // 여기 1 나중에 룸번호로 변경해야함
      body: JSON.stringify({
        roomId: 1,
        message,
        memberId: userInfo.memberId,
      }),
    });
  };

  useEffect(() => {
    console.log('퍼블리시 유저마커');
    publishMarker(markers);
  }, [markers]);

  return (
    <div>
      <Routes>
        {/* 룸으로 들어가도 search로 바로 route 연결되게 하고싶음 */}
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route path='schedule' element={<Schedule />} />
          <Route path='vote' element={<Vote />} />
          <Route
            path='chat'
            element={<Chat publishMessage={publishMessage} />}
          />
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}

export default Room;
