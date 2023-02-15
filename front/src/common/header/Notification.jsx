import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { roomInfoState } from '../../app/store';
import classes from './Notification.module.scss';

const colorCode = [
  '#EB5252',
  '#7997FE',
  '#90CE0A',
  '#61D9C3',
  '#8059D1',
  '#FF7BBA',
];

function Notification({ notificaiton, userInfo }) {
  const [roomInfo, setRoomInfo] = useRecoilState(roomInfoState);
  const [moveFlag, setMoveFlag] = useState(false);
  const navigate = useNavigate();
  const instance = axios.create({
    baseURL: `https://i8b202.p.ssafy.io/api`,
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const moveNavi = () => {
    return navigate('/room/search');
  };

  const onConfirm = async () => {
    console.log(notificaiton.readOrNot);
    if (!notificaiton.readOrNot) {
      try {
        const resNotification = await instance.patch('/notification', {
          notificationId: notificaiton.notificationId,
        });
        const resRoomInfo = await instance.get(`/rooms/${notificaiton.roomId}`);
        const resRoomMemList = await instance.get(
          `/rooms/users/${notificaiton.roomId}`
        );
        console.log(notificaiton.roomId);
        const colorCoded = colorCode[resRoomMemList.data.length];
        const resRoomRegist = await instance.post('/rooms/users', {
          roomId: notificaiton.roomId,
          colorCode: colorCoded,
        });
        console.log(resRoomRegist.data);
        setRoomInfo({
          roomId: resRoomInfo.data.roomId,
          roomName: resRoomInfo.data.roomName,
          startDate: resRoomInfo.data.startDate,
          endDate: resRoomInfo.data.endDate,
          colorCode: colorCoded,
        });
        moveNavi();
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <div className={classes.notificaiton_container}>
      <div className={classes.notificaiton_header}>
        <i className='bx bxs-bell-ring'></i>
        <p>{notificaiton.createdAt}</p>
      </div>
      <div className={classes.notificaiton_p}>
        {/* 부산갈래에 방 제목으로 바꾸기 */}
        <p>
          {notificaiton.sendMemberName}님의 &apos;{notificaiton.roomName}&apos;
          초대 되었습니다
        </p>
      </div>
      <div className={classes.btnList}>
        <button
          className={`${classes.btn} ${classes.confirmBtn}`}
          onClick={onConfirm}
        >
          수락
        </button>
        <button className={`${classes.btn} ${classes.rejectBtn}`}>거절</button>
      </div>
      <div className={classes.notificaiton_line}></div>
    </div>
  );
}

export default Notification;
