import React from 'react';

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
              {/* 용도 */}
              <div className={classes.login__main__signin__position__in}>
                <p>ID</p>
                <input type='text' placeholder='아이디를 입력해주세요.' />
                <p>PASSWORD</p>
                <input type='text' placeholder='비밀번호를 입력해주세요.' />
              </div>
              <button>Login</button>
            </div>

            <label htmlFor='id_save'>
              <input
                className={classes.id_save}
                type='checkbox'
                id='id_save'
                value='id_save'
              />
              아이디 저장하기 <span></span>
            </label>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
