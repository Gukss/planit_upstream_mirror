import React, { useRef, useEffect, useState, useCallback } from 'react';
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
  const [idValidMessage, setIdValidMessage] = useState('');

  const idChange = useCallback(
    e => {
      const loginID = e.target.value;
      setloginId(e.target.loginID);
      setIdValid(true);
      if (loginID.length < 5 || loginID.length > 15) {
        setIdValidMessage('5~15자의 영문, 숫자만 사용가능합니다');
        setIdValid(false);
      }
      const specialPattern = /[^\w\sㄱ-힣()0-9]/g;
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

  const pwChange = e => {
    setPw(e.target.value);
  };

  const pwCheckHandler = useCallback(
    e => {
      const pwCheck = e.target.value;
      setPwCheck(pwCheck);

      if (pw === pwCheck) {
        setPwCheckValid(true);
      } else {
        setPwCheckValid(false);
        setPwCheckValidMessage('비밀번호가 일치하지 않습니다');
      }
      if (pwCheck.length === 0) {
        setPwCheckValid(true);
      }
    },
    [pw]
  );

  // 제출 후 axios 요청하기
  const sumbmitHandler = e => {
    e.preventDefault();
    // 특정 조건을 다 만족시켜야만 회원가입이 가능해야함.
    // axios 요청 db에 -> 그러면 토큰이 발급됨.
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
              className={idValid ? classes.input_id : classes.invalid_input}
              onChange={idChange}
              ref={inputFocus}
              type='text'
            />
            <button className={classes.id_check}>확인</button>
          </label>

          {!idValid && (
            <div className={classes.invalid_message}>{idValidMessage}</div>
          )}
        </div>
        {/* 비밀번호 */}
        <div className={classes.input_wrap}>
          <p>비밀번호</p>
          <input
            className={classes.input_pw}
            onChange={pwChange}
            type='password'
          />
        </div>
        <div className={classes.input_wrap}>
          <p>비밀번호 확인</p>
          <input
            className={pwCheckValid ? classes.pw_valid : classes.invalid_input}
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
          <input className={classes.input_nickname} type='text' />
        </div>
        <div className={classes.input_wrap}>
          <p>이메일</p>
          <input className={classes.input_email} type='text' />
        </div>
        <button type='submit'>가입하기</button>
      </div>
    </form>
  );
}

export default SignUp;
