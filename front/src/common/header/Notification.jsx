import React, { useState } from 'react';

import classes from './Notification.module.scss';

function Notification({ notificaiton }) {
  const onConfirm = () => {};

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
