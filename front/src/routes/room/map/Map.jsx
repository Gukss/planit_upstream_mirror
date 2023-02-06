import React, { useState, useEffect, useRef } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import {
  searchedPlaces,
  searchedPlace,
  userMarkers,
  currentMarker,
} from '../../../app/store';
import Marker from './marker/Marker';
import './Map.scss';

const { kakao } = window;
let searchMarkers = []; // 마커정보 저장

// 커스텀오버레이 열기
const openOverlay = (map, overlay) => {
  return function () {
    overlay.setMap(map);
  };
};

// 검색된 마커 지우기
const removeMarker = () => {
  for (let i = 0; i < searchMarkers.length; i += 1) {
    searchMarkers[i].setMap(null);
  }
  searchMarkers = [];
};

function Map() {
  const mapContainer = useRef(null);
  const [map, setNewMap] = useState(null);
  const searchData = useRecoilValue(searchedPlaces);
  const clickData = useRecoilValue(searchedPlace);
  const [addMarker, addSetMarker] = useRecoilState(currentMarker); // 지금 선택한 마커 정보
  const [selectMarkers, setSelectMarkers] = useRecoilState(userMarkers); // 유저가 선택한 마커 모음

  // 초기 맵 셋팅
  const position = new kakao.maps.LatLng(33.450701, 126.570667);
  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };

  // map은 계속 호출이 되면 안되기 때문에 저장 x
  useEffect(() => {
    const map1 = new kakao.maps.Map(mapContainer.current, mapOptions);
    setNewMap(map1);
  }, []);

  // 검색 시 바로 여러 마커 표시되게 하는법
  useEffect(() => {
    console.log('너는 Map');
    console.log(selectMarkers);
    if (searchData.length >= 1) {
      removeMarker();
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
      const bounds = new kakao.maps.LatLngBounds();
      for (let i = 0; i < searchData.length; i += 1) {
        bounds.extend(new kakao.maps.LatLng(searchData[i].y, searchData[i].x));
        // displayMarker(searchData[i], map);
        const marker = new kakao.maps.Marker({
          map,
          position: new kakao.maps.LatLng(searchData[i].y, searchData[i].x),
        });

        const overlay = new kakao.maps.CustomOverlay({
          map,
          position: marker.getPosition(),
        });
        overlay.setMap(null); // 처음에 커스텀오버레이 안보여주기 위함

        // 마커 열고 닫기 시작!
        // 최상단 div 커스텀 오버레이
        const content = document.createElement('div');
        content.classList.add('overlay');
        // 커스텀 오버레이 제목
        const markerTitle = document.createElement('div');
        markerTitle.classList.add('overlay__title');
        markerTitle.appendChild(
          document.createTextNode(searchData[i].place_name)
        );
        content.appendChild(markerTitle);
        // 커스텀 오버레이 닫기
        const closeBtn = document.createElement('button');
        closeBtn.classList.add('overlay__close');
        closeBtn.appendChild(document.createTextNode('X'));
        closeBtn.onclick = function () {
          overlay.setMap(null);
        };
        markerTitle.appendChild(closeBtn);
        // 커스텀 오버레이로 이용자 마커 등록
        const markerAdd = document.createElement('button');
        markerAdd.classList.add('marker_add');
        markerAdd.appendChild(document.createTextNode('장소 추가 +'));
        // 유저 선택 마커 추가
        markerAdd.onclick = function () {
          console.log('usermarker');
          // 문제점 : 이제 이미 있는 거라면 추가 안시켜야함.... 어캐함?
          // 있 없 = t/ 없 있 =t / 없 없=t / 있 있 = f
          const id = Math.random();
          // const id = `marker_${idNum}`;
          // 현재 클릭한 값 찾기.
          addSetMarker({
            id,
            place_name: searchData[i].place_name,
            x: searchData[i].x,
            y: searchData[i].y,
          });
          // userMarker 배열에 추가
          setSelectMarkers(selectMarkers => [
            ...selectMarkers,
            {
              id,
              place_name: searchData[i].place_name,
              x: searchData[i].x,
              y: searchData[i].y,
            },
          ]);
        };
        content.appendChild(markerAdd);
        overlay.setContent(content);

        searchMarkers.push(marker);

        // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
        kakao.maps.event.addListener(
          marker,
          'click',
          openOverlay(map, overlay)
        );
      }

      map.setBounds(bounds); // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    }
  }, [searchData]);

  //
  // 클릭시 해당 위치로 이동.
  useEffect(() => {
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
  }, [clickData]);

  if (selectMarkers.length > 0) {
    return (
      <div className='map' ref={mapContainer}>
        <Marker map={map} />
      </div>
    );
  }
  return <div className='map' ref={mapContainer}></div>;
}

export default Map;
