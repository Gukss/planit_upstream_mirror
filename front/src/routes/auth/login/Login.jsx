import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import kakaoLogin from '../../../app/assets/images/kakao_login.jpg';
import naverLogin from '../../../app/assets/images/naver_login.png';
import classes from './Login.module.scss';
import { isLogin, userInfoState } from '../../../app/store';
import planitLogo from '../../../app/assets/images/planit_logo.svg';

function Login() {
  const [AppId, setAppId] = useState('');
  const [AppPwd, setAppPwd] = useState('');
  const [isLoginState, setIsLoginState] = useRecoilState(isLogin);
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const navigate = useNavigate();
  const idInput = useRef(null);

  useEffect(() => {
    idInput.current.focus();
  }, []);

  // 로그인 한 회원이 접속 시도 시, 메인페이지로 보내줌
  const checkLoginValue = isLoginState => {
    if (isLoginState) {
      navigate('/');
    }
  };

  // id 확인
  const handleIdChange = e => {
    setAppId(e.target.value);
  };

  // pwd 확인
  const handlePwdChange = e => {
    setAppPwd(e.target.value);
  };

  // 로그인 axios
  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const response = await axios.post(
        'https://i8b202.p.ssafy.io/api/sign-in',
        {
          memberAppId: AppId,
          memberAppPwd: AppPwd,
        }
      );
      // const jwtToken = response.data.token;
      // const cookies = new Cookies();
      // cookies.set('access', `Bearer ${jwtToken}`, {
      //   path: '/',
      //   sameSite: 'none',
      //   secure: true,
      // });
      setIsLoginState(true);
      setUserInfo({
        memberId: response.data.memberId,
        memberAppId: response.data.memberAppId,
        memberAppName: response.data.memberName,
        token: response.data.token,
      });

      navigate('/');
    } catch (error) {
      alert('아이디 및 비밀번호를 확인해주세요!');
    }
  };

  return (
    <div className={classes.login}>
      <div className={classes.login__bar}>
        <div className={classes.login__ticket}>
          PLAN!T T!CKET PLAN!T T!CKET PLAN!T T!CKET PLAN!T T!CKET PLAN!T T!CKET
          PLAN!T T!CKET PLAN!T T!CKET PLAN!T T!CKET PLAN!T TICKETPLANIT TICKET
          PLANIT TICKET PLANIT TICKET PLANIT TICKETPLANIT TICKET PLANIT TICKET
          PLANIT TICKET{' '}
        </div>
        <div className={classes.login__main}>
          <div className={classes.login__main__logo}>
            <img alt='logo' src={planitLogo} width='100%' height='100%' />
          </div>
          <div className={classes.login__main__signin}>
            {/* <h1>PLAN!T</h1> */}
            <form onSubmit={handleSubmit}>
              <div className={classes.login_box}>
                {/* ID, PW 입력 */}
                <div className={classes.login_inputbox}>
                  <p>ID</p>
                  <input
                    type='text'
                    placeholder='아이디를 입력해주세요.'
                    onChange={handleIdChange}
                    ref={idInput}
                  />
                  <p className={classes.login_inputbox_pwd}>PASSWORD</p>
                  <input
                    type='password'
                    placeholder='비밀번호를 입력해주세요.'
                    onChange={handlePwdChange}
                  />
                </div>
                {/* Login 버튼 */}
                <button>Login</button>
              </div>
            </form>

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
              <span className={classes.signUp}>
                아직 회원이 아니신가요?<Link to='/signup'>회원가입 하기</Link>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
