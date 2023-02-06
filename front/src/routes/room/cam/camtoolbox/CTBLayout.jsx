import React from 'react';
import CTBInvite from './CTBInvite';
import CTBFace from './CTBFace';
import CTBSound from './CTBSound';
import CTBMic from './CTBMic';

import './CTBLayout.scss';

function CTBLayout() {
  return (
    <div className='ctblist'>
      <div className='ctblist__item'>
        <CTBInvite />
        <CTBFace />
        <CTBSound />
        <CTBMic />
      </div>
    </div>
  );
}

export default CTBLayout;
