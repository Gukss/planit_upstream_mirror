import React, { useRef, useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
import { userInfoState } from '../../../app/store';
import classes from './SignUp.module.scss';

function SignUp() {
  // autofocus
  const inputFocus = useRef(null);
  useEffect(() => {
    inputFocus.current.focus();
  }, []);

  //
  // 아이디 유효성 검사
  const [loginId, setloginId] = useState('');
  const [idValid, setIdValid] = useState(true);
  const [realIdValid, setrealIdValid] = useState(false);
  const [idValidMessage, setIdValidMessage] = useState('');

  const idChange = useCallback(
    e => {
      const loginID = e.target.value;
      setloginId(prev => e.target.value);
      setrealIdValid(false);
      setIdValid(true);
      if (loginID.length < 5 || loginID.length > 15) {
        setIdValidMessage('5~15자의 영문, 숫자만 사용가능합니다');
        setIdValid(false);
      }
      const specialPattern = /[^\w\sㄱ-힣()0-9]/;
      if (specialPattern.test(loginID)) {
        setIdValidMessage('특수문자가 입력되었습니다.');
        setIdValid(false);
      }
      if (loginID.length === 0) {
        setIdValid(true);
      }
    },
    [loginId]
  );

  // 비밀번호 같은지 확인
  const [pw, setPw] = useState('');
  const [pwCheck, setPwCheck] = useState('');
  const [pwCheckValid, setPwCheckValid] = useState(true);
  const [pwCheckValidMessage, setPwCheckValidMessage] = useState('');
  const [realCheckValid, setRealCheckValid] = useState(false);

  const pwChange = e => {
    setPw(e.target.value);
  };

  const pwCheckHandler = useCallback(
    e => {
      const pwCheck = e.target.value;
      setPwCheck(pwCheck);

      if (pw === pwCheck) {
        setPwCheckValid(true);
        setRealCheckValid(true);
      } else {
        setPwCheckValid(false);
        setPwCheckValidMessage('비밀번호가 일치하지 않습니다');
        setRealCheckValid(false);
      }
      if (pwCheck.length === 0) {
        setPwCheckValid(true);
      }
    },
    [pw]
  );

  // userInfo 저장 & redirect를 위한 상태 저장
  const [Name, setMemberName] = useState('');

  const handleName = event => {
    setMemberName(event.target.value);
  };

  // Email 체크
  const [Email, setMemberEmail] = useState('');

  const handleEmail = event => {
    setMemberEmail(event.target.value);
  };

  const navigate = useNavigate();

  // 제출 후 axios 요청하기
  const sumbmitHandler = async e => {
    e.preventDefault();
    // 특정 조건을 다 만족시켜야만 회원가입이 가능해야함.
    // axios 요청 db에 -> 그러면 토큰이 발급됨.
    if (realIdValid && pwCheckValid) {
      try {
        const response = await axios.post(
          'https://i8b202.p.ssafy.io/api/members',
          {
            memberAppId: loginId,
            memberAppPwd: pw,
            memberName: Name,
            memberEmail: Email,
          }
        );
        if (response.status === 200) {
          alert('아이디가 생성되었습니다.');
          navigate('/');
        }
      } catch (error) {
        console.log(error);
        alert('아이디 생성에 실패하였습니다.');
      }
    } else if (!realIdValid) {
      alert('아이디 중복체크가 필요합니다.');
    } else {
      alert('양식이 올바르지 않습니다.');
    }
  };

  const handleRealIdValid = async event => {
    event.preventDefault();
    // const loginID = event.target[0].value;
    const loginID = loginId;
    if (!idValid) {
      alert('아이디를 올바르게 입력해주세요');
    } else {
      const response = await axios.get(
        `https://i8b202.p.ssafy.io/api/members/id/${loginID}`
      );
      if (!response.data) {
        alert('아이디가 중복되었습니다.');
      } else {
        setrealIdValid(response.data);
        setIdValidMessage('사용 가능합니다.');
      }
    }
  };

  const handleKeyPress = e => {
    console.log('ee');
    if (e.key === 'Enter') {
      console.log('enter');
      sumbmitHandler();
    }
  };

  return (
    <form className={classes.signup} onSubmit={sumbmitHandler}>
      {/* 로고자리 or 제목 */}
      <div className={classes.signup__title}>PLAN!T</div>
      <div className={classes.signup__id}>
        {/* 아이디 */}
        <div className={classes.input_wrap}>
          <p>아이디</p>
          <label>
            <input
              className={
                (idValid ? classes.input_id : classes.invalid_input,
                realIdValid ? classes.correct_input : classes.input_id)
              }
              onChange={idChange}
              ref={inputFocus}
              type='text'
            />
            <button
              type='submit'
              className={classes.id_check}
              onClick={handleRealIdValid}
            >
              확인
            </button>
          </label>

          {(!idValid && (
            <div className={classes.invalid_message}>{idValidMessage}</div>
          )) ||
            (realIdValid && (
              <div className={classes.correct_message}>{idValidMessage}</div>
            ))}
        </div>
        {/* 비밀번호 */}
        <div className={classes.input_wrap}>
          <p>비밀번호</p>
          <input
            className={
              realCheckValid ? classes.correct_input : classes.input_pw
            }
            onChange={pwChange}
            type='password'
          />
        </div>
        <div className={classes.input_wrap}>
          <p>비밀번호 확인</p>
          <input
            className={
              (pwCheckValid ? classes.pw_valid : classes.invalid_input,
              realCheckValid ? classes.correct_input : classes.pw_valid)
            }
            onChange={pwCheckHandler}
            type='password'
          />
          {/* 비밀번호 일치하지 않았을때 나오는 경고문구 */}
          {!pwCheckValid && (
            <div className={classes.invalid_message}>{pwCheckValidMessage}</div>
          )}
        </div>

        <div className={classes.input_wrap}>
          <p>닉네임</p>
          <input
            className={classes.input_nickname}
            type='text'
            onChange={handleName}
          />
        </div>
        <div className={classes.input_wrap}>
          <p>이메일</p>
          <input
            className={classes.input_email}
            type='text'
            onChange={handleEmail}
            onKeyDown={handleKeyPress}
          />
        </div>
        <button type='submit' onClick={sumbmitHandler}>
          가입하기
        </button>
      </div>
    </form>
  );
}

export default SignUp;
