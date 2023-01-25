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
            <h1> PLAN!T</h1>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
