import React, { useState, useEffect, useRef } from 'react';
import { useRecoilValue } from 'recoil';
import { searchedPlaces, searchedPlace } from '../../../app/store';
import './Map.scss';

const { kakao } = window;
let markers = []; // 마커정보 저장
// const title = '제목';
// const markerPositions = [
//   {
//     content: `<div class='title'>${title}</div>`,
//     latlng: new kakao.maps.LatLng(33.450936, 126.569477),
//     // latlng: new kakao.maps.LatLng(searchData[0].x, searchData[0].y),
//   },
// ];

// 마커 생성 및 지도 표시
const displayMarker = (place, map) => {
  const marker = new kakao.maps.Marker({
    map,
    position: new kakao.maps.LatLng(place.y, place.x),
  });
  marker.setMap(map);
  markers.push(marker);
};
// 마커 지우기
const removeMarker = () => {
  for (let i = 0; i < markers.length; i += 1) {
    markers[i].setMap(null);
  }
  markers = [];
};

function Map() {
  const mapContainer = useRef(null);
  const searchData = useRecoilValue(searchedPlaces);
  const clickData = useRecoilValue(searchedPlace);

  // 초기 맵 셋팅
  let position = new kakao.maps.LatLng(33.450701, 126.570667);
  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };

  const [map, setNewMap] = useState(null);

  useEffect(() => {
    const map1 = new kakao.maps.Map(mapContainer.current, mapOptions);
    setNewMap(map1);
  }, []);

  // 검색 시 바로 여러 마커 표시되게 하는법
  useEffect(() => {
    if (searchData.length >= 1) {
      removeMarker();
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
      const bounds = new kakao.maps.LatLngBounds();

      for (let i = 0; i < searchData.length; i += 1) {
        displayMarker(searchData[i], map);
        bounds.extend(new kakao.maps.LatLng(searchData[i].y, searchData[i].x));
      }
      map.setBounds(bounds); // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    }
  }, [searchData]);

  // 클릭시 해당 위치로 이동.
  useEffect(() => {
    if (Object.keys(clickData).length >= 1) {
      position = new kakao.maps.LatLng(clickData.x, clickData.y);
      // LatLngBounds 객체에 좌표를 추가합니다
      if (Object.keys(clickData).length > 0) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setCenter(new kakao.maps.LatLng(clickData.y, clickData.x));
        map.setLevel(0);
        // 마커 추가
        const marker = new kakao.maps.Marker({
          map,
          position,
        });
        marker.setMap(map);
      }
    }
  }, [clickData]);

  //   map.relayout();

  return (
    <div className='map' ref={mapContainer}>
      <div></div>
    </div>
  );
}

export default Map;
