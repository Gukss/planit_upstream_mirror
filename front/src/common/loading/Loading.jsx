import React from 'react';
import loading from '../../app/assets/images/loading.gif';
import classes from './Loading.module.scss';

function Loading() {
  return (
    <div className={classes.background}>
      <div className={classes.loadingText}>여행떠날 준비 하는중...</div>
      <img src={loading} alt='로딩중' width='5%' />
    </div>
  );
}

export default Loading;
