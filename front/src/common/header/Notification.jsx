import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { roomPK } from '../../app/store';
import classes from './Notification.module.scss';

function Notification({ notificaiton, userInfo }) {
  const [roomPk, setRoomPk] = useRecoilState(roomPK);
  console.log(notificaiton);
  const navigate = useNavigate();
  const instance = axios.create({
    baseURL: `https://i8b202.p.ssafy.io/api`,
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const onConfirm = async () => {
    setRoomPk(notificaiton.roomId);
    console.log(notificaiton.roomId);

    try {
      // const resNotification = await instance.patch(
      //   '/notification',
      //   notificaiton.notificationId
      // );
      // console.log(resNotification);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    if (roomPk === notificaiton.roomId) {
      navigate('/room/search');
    }
  }, [roomPk, notificaiton.roomId, navigate]);

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
