import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { isLogin, userInfoState } from '../../app/store';
import planitLogo from '../../app/assets/images/planit_logo.svg';
import NotificationList from './NotificationList';

import classes from './Header.module.scss';
import { ServiceFindNotification } from '../../service/notificationService';

function Header() {
  const userInfo = useRecoilValue(userInfoState);
  const [notificationList, setNotificationList] = useState();
  console.log(notificationList);
  console.log(userInfo);
  const login = useRecoilValue(isLogin);

  const FindNotification = async e => {
    // 나중에 토큰 값 던져 주세용!
    e.preventDefault();
    setNotificationList(await ServiceFindNotification(userInfo));
  };

  return (
    <>
      <header>
        <Link to='/'>
          <div className={classes.logoBtn}>
            <img src={planitLogo} alt='' />
            <span className={classes.logoText}>PLAN!T</span>
          </div>
        </Link>
        <div className={classes.right_menu}>
          {/* 비로그인상태 */}
          {!login && (
            <Link to='/login'>
              <button className={classes.login}>로그인</button>
            </Link>
          )}
          {/* 로그인상태 */}
          {login && (
            <div className={classes.is_login}>
              {/* 알람 */}
              <button className={classes.alarm} onClick={FindNotification}>
                <i className='bx bx-bell' />
              </button>
              {/* 프로필 */}
              <div className={classes.profile}>
                반갑습니다.
                <span className={classes.nickname}>
                  {userInfo.memberAppName}
                </span>
                님!
                <Link to='/mypage'>
                  <i className='bx bxs-user-circle' />
                </Link>
              </div>
            </div>
          )}
          {/* 방생성 */}
          <button className={classes.room_create}>
            <Link to='/createroom'>일정 만들기</Link>
          </button>
        </div>
      </header>
      {notificationList && (
        <NotificationList
          userInfo={userInfo}
          notificationList={notificationList}
          setNotificationList={setNotificationList}
        />
      )}
    </>
  );
}

export default Header;
