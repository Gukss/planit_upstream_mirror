import React from 'react';
import { useRecoilValue } from 'recoil';
import { isLogin } from '../../app/store';
import planitLogo from '../../app/assets/images/kakao_login.jpg';

import classes from './Header.module.scss';

function Header() {
  const login = useRecoilValue(isLogin);
  console.log(login);

  return (
    <header>
      <a className={classes.logoBtn} href='#!'>
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
            <div className={classes.alarm}>
              <i className='bx bx-bell' />
            </div>
            {/* 프로필 */}
            <div className={classes.profile}>
              반갑습니다. <span className={classes.nickname}>언도</span>님!
              <i className='bx bxs-user-circle' />
            </div>
          </div>
        )}
        {/* 방생성 */}
        <button className={classes.room_create}>일정 만들기</button>
      </div>
    </header>
  );
}

export default Header;
