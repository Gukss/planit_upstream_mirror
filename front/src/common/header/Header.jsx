import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { isLogin } from '../../app/store';
import planitLogo from '../../app/assets/images/kakao_login.jpg';
import NotificationList from './NotificationList';

import classes from './Header.module.scss';
import { ServiceFindNotification } from '../../service/notificationService';

function Header() {
  const [notificationList, setNotificationList] = useState();
  console.log(notificationList);
  const login = useRecoilValue(isLogin);

  const FindNotification = async () => {
    // 나중에 토큰 값 던져 주세용!
    const member = {
      memberId: 1,
      memberName: 'youngman',
      memberAppId: 'sksn12',
    };

    setNotificationList(await ServiceFindNotification(member));
  };

  return (
    <>
      <header>
        <a className={classes.logoBtn} href='http://localhost:3000/'>
          <img src={planitLogo} alt='' />
          <span className={classes.logoText}>PLAN!T</span>
        </a>
        <div className={classes.right_menu}>
          {/* 비로그인상태 */}
          {!login && <button className={classes.login}>로그인</button>}
          {/* 로그인상태 */}
          {login && (
            <div className={classes.is_login}>
              {/* 알람 */}
              <button className={classes.alarm} onClick={FindNotification}>
                <i className='bx bx-bell' />
              </button>
              {/* 프로필 */}
              <div className={classes.profile}>
                반갑습니다. <span className={classes.nickname}>언도</span>님!
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
          notificationList={notificationList}
          setNotificationList={setNotificationList}
        />
      )}
    </>
  );
}

export default Header;
