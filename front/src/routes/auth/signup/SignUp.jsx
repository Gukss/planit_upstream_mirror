import React, { useRef, useEffect } from 'react';
import classes from './SignUp.module.scss';

function SignUp() {
  // autofocus
  const inputFocus = useRef(null);
  useEffect(() => {
    inputFocus.current.focus();
  }, []);

  const sumbmitHandler = e => {
    // e.preventDefault();
  };

  return (
    <form className={classes.signup} onSubmit={sumbmitHandler}>
      {/* 로고자리 or 제목 */}
      <div className={classes.signup__title}>PLAN!T</div>
      <div className={classes.signup__id}>
        <p>아이디</p>
        <input className={classes.input_id} ref={inputFocus} type='text' />
        <p>비밀번호</p>
        <input className={classes.input_pw} type='password' />
        <p>비밀번호 확인</p>
        <input className={classes.input_pw_check} type='password' />
        <p>닉네임</p>
        <input className={classes.input_nickname} type='text' />
        <p>이메일</p>
        <input className={classes.input_email} type='text' />
      </div>
      <button type='submit'>가입하기</button>
    </form>
  );
}

export default SignUp;
