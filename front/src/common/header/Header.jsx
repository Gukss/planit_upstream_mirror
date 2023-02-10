import React from 'react';
import { Link } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { isLogin, userInfoState } from '../../app/store';
import planitLogo from '../../app/assets/images/kakao_login.jpg';

import classes from './Header.module.scss';

function Header() {
  const userInfo = useRecoilValue(userInfoState);
  const login = useRecoilValue(isLogin);
  console.log(login);
  console.log(userInfo);

  return (
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
            <div className={classes.alarm}>
              <i className='bx bx-bell' />
            </div>
            {/* 프로필 */}
            <div className={classes.profile}>
              반갑습니다.{' '}
              <span className={classes.nickname}>{userInfo.memberAppName}</span>
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
  );
}

export default Header;
