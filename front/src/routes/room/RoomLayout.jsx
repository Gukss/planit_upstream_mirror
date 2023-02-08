import React from 'react';
import Abar from './abar/Abar';
import Map from './map/Map';
import VideoComponent from './cam/VideoComponent';

import './RoomLayout.scss';

function RoomLayout() {
  return (
    <div className='roomlayout'>
      <Abar />
      <div className='map_camlist'>
        <Map />
        <VideoComponent />
      </div>
    </div>
  );
}

export default RoomLayout;
