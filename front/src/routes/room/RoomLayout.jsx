import React from 'react';
import Abar from './abar/Abar';
import Map from './map/Map';
import ControlBox from './map/controlbox/ControlBox';
import VideoComponent from './cam/VideoComponent';
import SaveIcon from '../../api/SaveIcon';

import './RoomLayout.scss';

function RoomLayout() {
  return (
    <div className='roomlayout'>
      <Abar />
      <div className='map_camlist'>
        <Map />
        <VideoComponent />
        <ControlBox />
        <VideoComponent />
        <SaveIcon />
      </div>
    </div>
  );
}

export default RoomLayout;
