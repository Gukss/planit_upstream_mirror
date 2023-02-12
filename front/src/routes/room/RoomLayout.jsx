import React from 'react';
import { useRecoilValue } from 'recoil';
import Abar from './abar/Abar';
import Map from './map/Map';
import ControlBox from './map/controlbox/ControlBox';
import VideoComponent from './cam/VideoComponent';
import SaveIcon from '../../api/SaveIcon';
import { roomPK } from '../../app/store';

import './RoomLayout.scss';

function RoomLayout() {
  const sessionRoom = useRecoilValue(roomPK);

  return (
    <div className='roomlayout'>
      <Abar />
      <div className='map_camlist'>
        <Map />
        <ControlBox />
        <VideoComponent sessionId={sessionRoom} />
        <SaveIcon />
      </div>
    </div>
  );
}

export default RoomLayout;
