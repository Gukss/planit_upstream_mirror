import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import { useRecoilValue, useSetRecoilState, useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
import {
  userInfoState,
  roomInfoState,
  userMarkers,
  scheduleInfo,
  chatMessages,
} from '../../../app/store';
import classes from './RoomManageItem.module.scss';

function RoomManageItem(props) {
  const navigate = useNavigate();
  const [roomInfo, setRoomInfo] = useRecoilState(roomInfoState);
  const [forLoadMarker, setForLoadMarker] = useRecoilState(userMarkers);
  const [chatMessa, setChatMessa] = useRecoilState(chatMessages);
  const userInfo = useRecoilValue(userInfoState);
  const resJoinMem = useRef('');
  const [memNameList, setMemNameList] = useState('');
  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const joinMemberInfo = async e => {
    try {
      resJoinMem.current = await instance.get(
        `/rooms/users/${props.roomData.roomId}`
      );
      setMemNameList(
        await resJoinMem.current.data.map(memName => `${memName.memberName}, `)
      );
      console.log(resJoinMem.current.data);
      console.log(memNameList.current);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    joinMemberInfo();
  }, []);

  const handleSubmitItem = async e => {
    const roomID = props.roomData.roomId;
    console.log(roomID);
    try {
      // 방 정보 가져오기
      const resRoomInfo = await instance.get(`/rooms/${roomID}`);
      setRoomInfo({
        roomId: resRoomInfo.data.roomId,
        roomName: resRoomInfo.data.roomName,
        startDate: resRoomInfo.data.startDate,
        endDate: resRoomInfo.data.endDate,
        colorCode: props.roomInfo.colorCode,
      });
      // 보관함 정보 가져오기
      const storageInfo = await instance.get(`/storages/rooms/${roomID}`);
      console.log('이거슨 스토리쥐');
      console.log(storageInfo.data);
      if (storageInfo.data.length > 0) {
        const storageMarkerData = storageInfo.data.map(marker =>
          // const markerColor = await instance.get('')
          setForLoadMarker(forLoadMarker => [
            ...forLoadMarker,
            {
              x: marker.lng,
              y: marker.lat,
              isconfirmed: marker.confirmed,
              categoryCode: marker.categoryName,
              title: marker.storageName,
              colorCode: marker.colorCode,
            },
          ])
        );
        // // 스케쥴 관련 store 정리
        // const scheduleData = {};
        // const storageSchedule = storageInfo.data.filter(
        //   data => data.confirmed !== false
        // );
        // if (storageSchedule.length > 0) {
        //   // max 값 구하기
        //   let maxDayOrder = 0;
        //   for (let i = 0; i < storageSchedule.length; i++) {
        //     if (maxDayOrder < storageSchedule[i].dayOrder) {
        //       maxDayOrder = storageSchedule[i].dayOrder;
        //     }
        //   }

        // }
      }
      // 채팅 가져오기
      const chatInfo = await instance.get(`/chatting/${roomID}`);
      setChatMessa(chatInfo.data);
      console.log(chatInfo.data);
      // 투표 나중에
      const voteInfo = await instance.get(`/votes/${roomID}`);
      console.log(voteInfo.data);
      // if (voteInfo.data.length > 0) {
      // // }
      if (roomInfo.roomId !== -1) {
        setTimeout(() => navigate('/room/search'), 1000);
      } else {
        console.log(roomInfo);
        navigate('/room/search');
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div className={classes.room_manageitem}>
      <section className={classes.title_section}>
        <p className={classes.title}>{props.roomData.roomName}</p>
        <p className={classes.date}>
          {props.roomData.startDate}~{props.roomData.endDate}
        </p>
      </section>
      <hr />
      <section className={classes.content_section}>
        <p className={classes.created_at}>생성일 : 2023.01.01</p>
        <p className={classes.participants}>
          참여자:
          {memNameList}
        </p>
      </section>
      <section className={classes.button_section}>
        <button className={classes.button_remove}>삭제</button>
        <button className={classes.button_enter} onClick={handleSubmitItem}>
          입장
        </button>
      </section>
    </div>
  );
}

export default RoomManageItem;
