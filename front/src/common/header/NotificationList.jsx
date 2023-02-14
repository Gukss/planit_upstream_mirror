import React, { useState } from 'react';

import classes from './NotificationList.module.scss';
import Notification from './Notification';

function NotificationList({ notificationList, setNotificationList, userInfo }) {
  const onDelete = () => {
    setNotificationList('');
  };
  console.log(notificationList.data);

  return (
    <div className={classes.notificationlist}>
      <div className={classes.notificationList_header}>
        <p>알림</p>
        <button onClick={onDelete}>
          <i className='bx bx-x'></i>
        </button>
      </div>
      <div className={classes.notificationList_header_line}></div>
      {notificationList &&
        notificationList.data
          .filter(tmp => !tmp.readOrNot)
          .map(notificaiton => (
            <Notification notificaiton={notificaiton} userInfo={userInfo} />
          ))}
    </div>
  );
}

export default NotificationList;
