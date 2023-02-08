import React, { useEffect, useState } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import {
  userMarkers,
  currentMarker,
  removeInformation,
} from '../../../../app/store';
import './Marker.scss';

const { kakao } = window;

function Marker(props) {
  const existMap = props.map;
  const selectMarker = useRecoilValue(currentMarker);
  const [roomMarkers, setRoomMarkers] = useState([]);
  const [removeMarker, setRemoveMarker] = useRecoilState(removeInformation);
  const [userSelectMarkers, setUserSelectMarker] = useRecoilState(userMarkers);

  const openOverlay = (map, overlay) => {
    return function () {
      overlay.setMap(map);
    };
  };

  // 유저가 장소 마커 등록했을 때만 표시하게.
  useEffect(() => {
    console.log('너는 마커');
    const pos = userSelectMarkers.length - 1;

    const marker = new kakao.maps.Marker({
      map: existMap,
      position: new kakao.maps.LatLng(
        userSelectMarkers[pos].y,
        userSelectMarkers[pos].x
      ),
    });
    marker.setMap(existMap);

    setRoomMarkers([
      ...roomMarkers,
      {
        id: userSelectMarkers[pos].id,
        marker,
      },
    ]);

    const overlay = new kakao.maps.CustomOverlay({
      map: existMap,
      position: marker.getPosition(),
    });

    overlay.setMap(null); // 처음에 커스텀오버레이 안보여주기 위함

    // 최상단 div 커스텀 오버레이
    const content = document.createElement('div');
    content.classList.add('overlay');
    // 커스텀 오버레이 제목
    const markerTitle = document.createElement('div');
    markerTitle.classList.add('overlay__title');
    markerTitle.appendChild(
      document.createTextNode(userSelectMarkers[pos].title)
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
    // 이용자 마커 제거
    const markerRemove = document.createElement('button');
    markerRemove.classList.add('marker_remove');
    markerRemove.appendChild(document.createTextNode('장소 제거 -'));
    markerRemove.onclick = function () {
      // recoilstate에서 해당 마커 지우기.
      overlay.setMap(null);
      marker.setMap(null);
      const removeInfo = {
        id: userSelectMarkers[pos].id,
        y: userSelectMarkers[pos].y,
        x: userSelectMarkers[pos].x,
      };
      setRemoveMarker(removeInfo);
    };
    content.appendChild(markerRemove);
    overlay.setContent(content);

    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
    kakao.maps.event.addListener(
      marker,
      'click',
      openOverlay(existMap, overlay)
    );
  }, [selectMarker]);

  useEffect(() => {
    // 처음에 생성했을때 룸마커가 비어있기에 필터링이안됨
    // 지금 문제점은 그냥 처음에 생성했을때 다 문제가 됨.

    if (roomMarkers.length > 0) {
      console.log('마커필터링');

      // 선택한 마커 정보만 따로 빼기.
      const roomMarker = roomMarkers.filter(
        roomMarker => roomMarker.id === removeMarker.id
      );
      // const check = roomMarkers.findIndex(i => i.id === roomMarker[0].id);

      setRoomMarkers(roomMarkers.filter(room => room.id !== roomMarker[0].id));

      roomMarker[0].marker.setMap(null);

      setUserSelectMarker(
        userSelectMarkers.filter(
          usermarker => usermarker.id !== removeMarker.id
        )
      );
    }
  }, [removeMarker]);

  return (
    <div className='user_marker'>
      <div></div>
    </div>
  );
}

export default Marker;
