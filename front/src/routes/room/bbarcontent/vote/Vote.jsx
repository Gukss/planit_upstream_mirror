import { useRecoilValue } from 'recoil';
import React, { useState } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Vote.module.scss';
import VoteList from './VoteList';
import { userMarkers } from '../../../../app/store';

function Vote() {
  return (
    <Bbar>
      <div className={classes.vote_title}>
        <div className={classes.title_title}>투표</div>
        <p>
          여행 일정을 짜는 중 의견이 정해지지 않을 땐 투표를 만들어
          진행해보세요.
        </p>
      </div>
      <div className={classes.vote_body}>
        <div className={classes.vote_body_head}>
          <p>투표 내역</p>
          <i className='bx bx-plus'></i>
        </div>
        <hr />
        <VoteList />
      </div>
    </Bbar>
  );
}

export default Vote;
