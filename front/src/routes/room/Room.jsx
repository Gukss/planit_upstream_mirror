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
  markerFlag,
  userInfoState,
  roomInfoState,
  voteInformation,
  voteFlag,
  scheduleInfo,
} from '../../app/store';

function Room() {
  const client = useRef({});
  const publishMarkerFlag = useRecoilValue(markerFlag);
  const publishVoteFlag = useRecoilValue(voteFlag);
  // const [sClient, setSClient] = useRecoilState(stompClient);
  const [votes, setVotes] = useRecoilState(voteInformation);
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const [messages, setMessages] = useRecoilState(chatMessages);
  const [socketMarker, setSocketMarker] = useRecoilState(socketMarkers);
  const userInfo = useRecoilValue(userInfoState);
  const roomInfo = useRecoilValue(roomInfoState);
  const [presentSche, setPresentSche] = useRecoilState(scheduleInfo); // 일정 정보

  console.log('여기는 룸 입니다.', roomInfo);

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeChatting = async () => {
    console.log('채팅');
    await client.current.subscribe(
      `/sub/room/${roomInfo.roomId}`,
      ({ body }) => {
        console.log(body);
        setMessages(pre => [...pre, JSON.parse(body)]);
      }
    );
  };

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeMarkers = async () => {
    console.log('마커 subscribe');
    await client.current.subscribe(
      `/sub/markers/${roomInfo.roomId}`,
      ({ body }) => {
        setMarkers(JSON.parse(body).storageItemList);
        // setSocketMarker(JSON.parse(body).storageItemList);
        // console.log('마커', body);
        // console.log('파스 리스트', JSON.parse(body).storageItemList);
      }
    );
  };

  const subscribeVotes = async () => {
    console.log('투표 구독');
    await client.current.subscribe(
      `/sub/votes/${roomInfo.roomId}`,
      ({ body }) => {
        // setVotes(pre => [...pre, JSON.parse(body).votesList]);
        setVotes(JSON.parse(body).votesList);
        console.log('투표 구독 정보', JSON.parse(body).votesList);
      }
    );
  };

  const subscribeSchedule = async () => {
    console.log('일정 구독');
    await client.current.subscribe(
      `/sub/schedule/${roomInfo.roomId}`,
      ({ body }) => {
        // setVotes(pre => [...pre, JSON.parse(body).votesList]);
        // setVotes(JSON.parse(body).votesList);
        // console.log('투표 구독 정보', JSON.parse(body).votesList);
        console.log('========', JSON.parse(body));
        setPresentSche(JSON.parse(body).presentSche);
        // setMessages(pre => [...pre, JSON.parse(body)]);
      }
    );
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
        subscribeVotes();
        subscribeSchedule();
      },
      onStompError: frame => {
        console.error(frame);
      },
    });
    console.log('connect');
    await stompActive();
  };

  // 웹 소켓 끊기.
  const disconnect = () => {
    console.log('disconnect');
    client.current.deactivate();
  };

  // 방 시작 후 웹 소켓 연결
  useEffect(() => {
    console.log('방 처음');
    connect();
    return () => disconnect();
  }, []);

  // 웹소켓 투표
  const publishVote = async votes => {
    console.log('publish vote');
    if (!client.current.connected) {
      return;
    }
    console.log('publish vote Info: ', votes);
    await client.current.publish({
      destination: '/pub/votes',
      body: JSON.stringify({
        roomId: roomInfo.roomId,
        votesList: [...votes],
      }),
      // body: { roomId: 1, markers },
    });
  };

  // 웹소켓 일정
  const publishSchedule = async schedule => {
    console.log('publish schedule');
    if (!client.current.connected) {
      return;
    }
    console.log('publish schedule Info: ', schedule);
    await client.current.publish({
      destination: '/pub/schedule',
      body: JSON.stringify({
        roomId: roomInfo.roomId,
        presentSche: schedule.map(item => {
          return { items: item.items, date: item.date };
        }),
      }),
      // body: { roomId: 1, markers },
    });
  };

  const publishMarker = async markers => {
    console.log('publish marker');
    if (!client.current.connected) {
      return;
    }
    console.log('publish markers info: ', markers);
    await client.current.publish({
      destination: '/pub/markers',
      // 여기 1 나중에 룸번호로 변경해야함
      body: JSON.stringify({
        roomId: roomInfo.roomId,
        storageItemList: [...markers],
      }),
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
        roomId: roomInfo.roomId,
        message,
        memberId: userInfo.memberId,
      }),
    });
  };

  // publish Marker 호출용
  useEffect(() => {
    console.log('퍼블리시 유저마커');
    publishMarker(markers);
  }, [publishMarkerFlag]);

  // publish Vote 호출용
  useEffect(() => {
    console.log('퍼블리시 투표');
    publishVote(votes);
  }, [publishVoteFlag]);

  return (
    <div>
      <Routes>
        {/* 룸으로 들어가도 search로 바로 route 연결되게 하고싶음 */}
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route
            path='schedule'
            element={<Schedule publishSchedule={publishSchedule} />}
          />
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
