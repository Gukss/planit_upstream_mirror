import React from 'react';
import classes from './SignUp.module.scss';

function SignUp() {
  return (
    <div className={classes.signup}>
      {/* 로고자리 or 제목 */}
      <header>PLAN!T</header>
      <div className={classes.signup__id}>
        <p>아이디</p>
        <input className={classes.input_id} type='text' />
        <p>비밀번호</p>
        <input className={classes.input_pw} type='text' />
        <p>비밀번호 확인</p>
        <input className={classes.input_pw_check} type='text' />
        <p>닉네임</p>
        <input className={classes.input_nickname} type='text' />
        <p>이메일</p>
        <input className={classes.input_email} type='text' />
      </div>
      <button>가입하기</button>
    </div>
  );
}

export default SignUp;
