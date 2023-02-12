import React, { useState } from 'react';

import classes from './NotificationList.module.scss';
import Notification from './Notification';

function NotificationList({ notificationList, setNotificationList }) {
  const onDelete = () => {
    setNotificationList('');
  };

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
        notificationList.data.map(notificaiton => (
          <Notification notificaiton={notificaiton} />
        ))}
    </div>
  );
}

export default NotificationList;
