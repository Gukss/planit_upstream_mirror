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

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeChatting = async () => {
    await client.current.subscribe(
      `/sub/room/${roomInfo.roomId}`,
      ({ body }) => {
        setMessages(pre => [...pre, JSON.parse(body)]);
      }
    );
  };

  // 여기 1 나중에 룸번호로 변경해야함
  const subscribeMarkers = async () => {
    await client.current.subscribe(
      `/sub/markers/${roomInfo.roomId}`,
      ({ body }) => {
        setMarkers(JSON.parse(body).storageItemList);
        // setSocketMarker(JSON.parse(body).storageItemList);
      }
    );
  };

  const subscribeVotes = async () => {
    await client.current.subscribe(
      `/sub/votes/${roomInfo.roomId}`,
      ({ body }) => {
        // setVotes(pre => [...pre, JSON.parse(body).votesList]);
        setVotes(JSON.parse(body).votesList);
      }
    );
  };

  const subscribeSchedule = async () => {
    await client.current.subscribe(
      `/sub/schedule/${roomInfo.roomId}`,
      ({ body }) => {
        // setVotes(pre => [...pre, JSON.parse(body).votesList]);
        // setVotes(JSON.parse(body).votesList);
        // console.log('투표 구독 정보', JSON.parse(body).votesList);
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
      debug: str => {},
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        subscribeChatting();
        subscribeMarkers();
        subscribeVotes();
        subscribeSchedule();
      },
      onStompError: frame => {
        console.error(frame);
      },
    });
    await stompActive();
  };

  // 웹 소켓 끊기.
  const disconnect = () => {
    client.current.deactivate();
  };

  // 방 시작 후 웹 소켓 연결
  useEffect(() => {
    connect();
    return () => disconnect();
  }, []);

  // 웹소켓 투표
  const publishVote = async votes => {
    if (!client.current.connected) {
      return;
    }
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
    if (!client.current.connected) {
      return;
    }
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
    if (!client.current.connected) {
      return;
    }
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
        colorCode: roomInfo.colorCode,
      }),
    });
  };

  // publish Marker 호출용
  useEffect(() => {
    publishMarker(markers);
  }, [publishMarkerFlag]);

  // publish Vote 호출용
  useEffect(() => {
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
