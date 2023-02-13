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
import { chatMessages, stompClient, userMarkers } from '../../app/store';

function Room() {
  const client = useRef({});
  const [sClient, setSClient] = useRecoilState(stompClient);
  const [messages, setMessages] = useRecoilState(chatMessages);
  const [markers, setMarkers] = useRecoilState(userMarkers);

  console.log('여기는 룸 입니다.', sClient);

  useEffect(() => {
    console.log('퍼블리시 유저마커');
  }, [userMarkers]);

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeChatting = async () => {
    console.log('채팅');
    await sClient.subscribe('/sub/room/1', ({ body }) => {
      console.log(body);
      setMessages(messages => [...messages, JSON.parse(body)]);
    });
  };

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeMarkers = async () => {
    console.log('subscribe');
    await sClient.subscribe('/sub/markers/1', ({ body }) => {
      console.log(body);
    });
  };

  const newStomp = new StompJs.Client({
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
      subscribeChatting();
      subscribeMarkers();
    },
    onStompError: frame => {
      console.error(frame);
    },
  });

  const stompActive = async () => {
    console.log('client 등록 시점');
    console.log(sClient);
    newStomp.activate();
  };

  const setStompClient = async () => {
    console.log('client 값 => state 관리 시점');
    setSClient(newStomp);
  };

  const connect = async () => {
    console.log('connect');
    await stompActive();
    await setStompClient();
  };

  const disconnect = () => {
    console.log('disconnect');
    sClient.deactivate();
  };

  useEffect(() => {
    console.log('방 처음');
    connect();
    return () => disconnect();
  }, []);

  return (
    <div>
      <Routes>
        {/* 룸으로 들어가도 search로 바로 route 연결되게 하고싶음 */}
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route path='schedule' element={<Schedule />} />
          <Route path='vote' element={<Vote />} />
          <Route path='chat' element={<Chat />} />
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}

export default Room;
