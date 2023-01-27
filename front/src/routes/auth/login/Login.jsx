import React from 'react';
import kakaoLogin from '../../../app/assets/images/kakao_login.jpg';
import naverLogin from '../../../app/assets/images/naver_login.png';
import classes from './Login.module.scss';

function Login() {
  return (
    <div className={classes.login}>
      <div className={classes.login__bar}>
        <div className={classes.login__main}>
          <div className={classes.login__main__logo}>
            <i className='bx bx-box' />
          </div>
          <div className={classes.login__main__signin}>
            <h1>PLAN!T</h1>
            <div className={classes.login__main__signin__position}>
              {/* ID, PW 입력 */}
              <div className={classes.login__main__signin__position__in}>
                <p>ID</p>
                <input type='text' placeholder='아이디를 입력해주세요.' />
                <p>PASSWORD</p>
                <input type='text' placeholder='비밀번호를 입력해주세요.' />
              </div>
              <button>Login</button>
            </div>

            {/* ID 저장 및 패스워드 찾기 줄 */}
            <label htmlFor='id_save'>
              <input
                className={classes.id_save}
                type='checkbox'
                id='id_save'
                value='id_save'
              />
              아이디 저장하기
              <a href='#!'>아이디/비밀번호 찾기</a>
            </label>
            {/* 소셜로그인 줄 */}
            <div className={classes.member_check}>
              <span>
                아직 회원이 아니신가요?<a href='#!'>회원가입 하기</a>
              </span>
              <span>
                간편 로그인
                <img src={kakaoLogin} alt='' />
                <img src={naverLogin} alt='' />
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
