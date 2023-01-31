import React, { useEffect, useRef } from 'react';
import classes from './Map.module.scss';

function Map() {
  const mapContainer = useRef(null);
  const { kakao } = window;
  const position = new kakao.maps.LatLng(33.450701, 126.570667);
  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };

  useEffect(() => {
    const map = new kakao.maps.Map(mapContainer.current, mapOptions);
  });
  return (
    <div className={classes.map} ref={mapContainer}>
      <div></div>
    </div>
  );
}

export default Map;
