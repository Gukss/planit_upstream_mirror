import React, { useEffect, useRef } from 'react';
import classes from './Map.module.scss';

function Map() {
  const mapElement = useRef(null);

  useEffect(() => {
    const { naver } = window;
    if (!mapElement.current || !naver) return;

    const location = new naver.maps.LatLng(37.5656, 126.9769);
    const mapOptionsContent = {
      center: location,
      zoom: 17,
      zoomControl: true,
      zoomControlOptions: {
        position: naver.maps.Position.TOP_RIGHT,
      },
    };
    naver.maps.MapOptions = { mapOptionsContent };
    const mapOptionsCreated = naver.maps.MapOptions;
    const mapOptions = mapOptionsCreated;

    const map = new naver.maps.Map(mapElement.current, mapOptions);
    const newOne = new naver.maps.Marker({
      position: location,
      map,
    });
  }, []);

  return (
    <div className={classes.map}>
      <div ref={mapElement} className='Map' style={{ minHeight: '1080px' }} />
    </div>
  );
}

export default Map;
