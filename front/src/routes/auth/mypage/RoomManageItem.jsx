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
  loadingState,
} from '../../../app/store';
import classes from './RoomManageItem.module.scss';

function RoomManageItem(props) {
  const navigate = useNavigate();
  const [loading, setLoading] = useRecoilState(loadingState);
  const [scheduleInfoState, setScheduleInfoState] =
    useRecoilState(scheduleInfo);
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
    setLoading(true);
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
      console.log(storageInfo);
      if (storageInfo.data.length > 0) {
        const storageMarkerData = storageInfo.data.map(marker =>
          // const markerColor = await instance.get('')
          setForLoadMarker(forLoadMarker => [
            ...forLoadMarker,
            {
              id: marker.id,
              x: marker.lng,
              y: marker.lat,
              isConfirmed: marker.confirmed,
              categoryCode: marker.categoryName,
              title: marker.storageName,
              colorCode: marker.colorCode,
            },
          ])
        );
        // // 스케쥴 관련 store 정리
        const storageSchedule = await storageInfo.data.filter(
          storageData => storageData.confirmed !== false
        );
        if (storageSchedule.length > 0) {
          // max 값 구하기
          let maxDayOrder = 0;
          for (let i = 0; i < storageSchedule.length; i += 1) {
            if (maxDayOrder < storageSchedule[i].dayOrder) {
              maxDayOrder = storageSchedule[i].dayOrder;
            }
          }
          if (maxDayOrder === 0) {
            const scheduleAllData = {
              date: resRoomInfo.data.startDate,
              items: [],
            };
            for (let i = 0; i < storageSchedule.length; i += 1) {
              if (storageSchedule[i].indexOrder === i) {
                scheduleAllData.items.push(storageSchedule[i]);
              }
            }
          } else {
            const scheduleAllData = {};
            const AddDays = (date, day) => {
              const result = new Date(date);
              result.setDate(result.getDate() + day);
              return result;
            };
            for (let j = 0; j < maxDayOrder; j += 1) {
              const filteredSc = storageSchedule.filter(
                storageSchedule.dayOrder === j
              );
              const forSetDate = AddDays(resRoomInfo.data.startDate, j);
              const scheduleIndiData = {
                data: forSetDate,
                items: [],
              };
              for (let k = 0; k < filteredSc.length; k += 1) {
                if (k === filteredSc[k].indexOrder) {
                  scheduleIndiData.items.push(filteredSc[k]);
                }
              }
              scheduleAllData.push(scheduleIndiData);
            }
            setScheduleInfoState(scheduleAllData);
          }
        }
      } else {
        setForLoadMarker([]);
      }
      // 채팅 가져오기
      const chatInfo = await instance.get(`/chatting/${roomID}`);
      if (chatInfo.data.length > 0) {
        setChatMessa(chatInfo.data);
        console.log(chatInfo.data);
      } else {
        setChatMessa([]);
      }
      // // 투표 나중에
      // const voteInfo = await instance.get(`/votes/${roomID}`);
      // console.log(voteInfo.data);
      // if (voteInfo.data.length > 0) {
      // // }
      setTimeout(() => {
        setLoading(false);
        navigate('/room/search');
      }, 1500);
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
