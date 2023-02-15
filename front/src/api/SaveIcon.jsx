import React from 'react';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import {
  userInfoState,
  scheduleInfo,
  userMarkers,
  roomInfoState,
  chatMessages,
} from '../app/store';

import classes from './SaveIcon.module.scss';

// https://www.flaticon.com/kr/free-icon/save-file_423081?term=%EC%A0%80%EC%9E%A5&page=8&position=3&origin=search&related_id=423081
function SaveIcon() {
  const userInfo = useRecoilValue(userInfoState);
  const scheduleInFo = useRecoilValue(scheduleInfo);
  const userMarkerInfo = useRecoilValue(userMarkers);
  const roomInfo = useRecoilValue(roomInfoState);
  const chatInfo = useRecoilValue(chatMessages);

  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const sendAxios = async e => {
    try {
      const roomPatchData = {
        roomId: roomInfo.roomId,
        roomName: roomInfo.roomName,
        startDate: roomInfo.startDbate,
        endDate: roomInfo.endDate,
      };
      // const saveRoomPatch = await instance.patch('/rooms', roomPatchData);
      chatInfo.map(chat =>
        instance.post('/chatting', {
          roomId: chat.roomId,
          message: chat.message,
        })
      );
      const storageConfirm = [];
      const storageNotConfirm = userMarkerInfo
        .filter(userMark => !userMark.isConfirmed)
        .map(userMark =>
          userMark.categoryCode
            ? {
                lng: userMark.x,
                lat: userMark.y,
                confirmed: userMark.isConfirmed,
                categoryName: userMark.categoryCode,
                storageName: userMark.title,
                dayOrder: 0,
                indexOrder: 0,
                roomId: roomInfo.roomId,
                colorCode: userMark.colorCode,
                // saved: roomInfo.saved + 1
              }
            : {
                lng: userMark.x,
                lat: userMark.y,
                confirmed: userMark.isConfirmed,
                categoryName: '-',
                storageName: userMark.title,
                dayOrder: 0,
                indexOrder: 0,
                roomId: roomInfo.roomId,
                colorCode: userMark.colorCode,
              }
        );
      const schedules = Object.values(scheduleInFo).map((schedule, j) => {
        if (schedule.items.length > 0) {
          const item = schedule.items.map((scMarker, i) => {
            return storageConfirm.push({
              lng: scMarker.x,
              lat: scMarker.y,
              confirmed: scMarker.isConfirmed,
              category: scMarker.categoryCode,
              storageName: scMarker.title,
              indexOrder: i,
              dayOrder: j,
              roomId: roomInfo.roomId,
              colorCode: scMarker.colorCode,
            });
          });
          return item;
        }
        return [];
      });
      storageConfirm.map(confirmes => instance.post('/storages', confirmes));
      storageNotConfirm.map(notCon => instance.post('/storages', notCon));
      alert('저장되었습니다.');
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div
      className={classes.save}
      onClick={sendAxios}
      onKeyDown={sendAxios}
      tabIndex={0}
      role='button'
    >
      <img src='https://cdn-icons-png.flaticon.com/512/423/423081.png' alt='' />
      {/* <img
        src='https://cdn-icons-png.flaticon.com/512/4087/4087925.png'
        alt=''
      /> */}
    </div>
  );
}

export default SaveIcon;
