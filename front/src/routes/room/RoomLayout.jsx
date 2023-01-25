import React from 'react';
import Abar from './abar/Abar';
import CamList from './cam/CamList';

import './RoomLayout.scss';

function RoomLayout() {
  return (
    <div className='roomlayout'>
      <Abar />
      <CamList />
    </div>
  );
}

export default RoomLayout;
