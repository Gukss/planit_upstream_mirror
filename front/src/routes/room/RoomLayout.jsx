import React from 'react';
import Abar from './abar/Abar';
import Map from './map/Map';
import CamList from './cam/CamList';

import './RoomLayout.scss';

function RoomLayout() {
  return (
    <div className='roomlayout'>
      <Abar />
      <Map />
      <CamList />
    </div>
  );
}

export default RoomLayout;
