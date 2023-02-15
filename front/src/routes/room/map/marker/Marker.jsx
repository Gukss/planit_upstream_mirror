import React, { useEffect, useState, useRef } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';

import * as StompJs from '@stomp/stompjs';
import SockJS from 'sockjs-client';

import {
  userMarkers,
  currentMarker,
  removeInformation,
  categoryCheck,
  socketMarkers,
  stompClient,
  markerFlag,
  roomInfoState,
} from '../../../../app/store';
import pinkMarker from '../../../../app/assets/images/marker_pink.png';
import redMarker from '../../../../app/assets/images/marker_red.png';
import purpleMarker from '../../../../app/assets/images/marker_purple.png';
import greenMarker from '../../../../app/assets/images/marker_green.png';
import emeraldMarker from '../../../../app/assets/images/marker_emerald.png';
import blueMarker from '../../../../app/assets/images/marker_blue.png';
import './Marker.scss';

const { kakao } = window;

function Marker(props) {
  const existMap = props.map;
  const category = useRecoilValue(categoryCheck);
  const roomInfo = useRecoilValue(roomInfoState);
  const selectMarker = useRecoilValue(currentMarker);
  const [roomMarkers, setRoomMarkers] = useState([]);
  const [removeMarker, setRemoveMarker] = useRecoilState(removeInformation);
  const [userSelectMarkers, setUserSelectMarker] = useRecoilState(userMarkers);
  const [publishMarkerFlag, setPublishMarkerFlag] = useRecoilState(markerFlag);

  // 유저 색깔 마커
  const userColor = roomInfo.colorCode;
  console.log(userSelectMarkers);
  console.log('리랜더링 되나유?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');

  // useEffect(() => {
  //   console.log('marker컴포넌트');
  //   if (socketMarkers.length > 0) {
  //     setUserSelectMarker(socketMarkers);
  //   }
  // }, [socketMarkers]);

  const colorList = [
    { color: '#EB5252', img: redMarker },
    { color: '#7997FE', img: blueMarker },
    { color: '#90CE0A', img: greenMarker },
    { color: '#61D9C3', img: emeraldMarker },
    { color: '#8059D1', img: purpleMarker },
    { color: '#FF7BBA', img: pinkMarker },
  ];

  let myMarkerImg = userColor;
  console.log(userSelectMarkers);

  if (userSelectMarkers.length > 0) {
    const pos = userSelectMarkers.length - 1;
    console.log('dhfbelqrld');
    console.log(pos);
    console.log(userSelectMarkers);
    const markerColor = colorList.filter(
      item => item.color === userSelectMarkers[pos].colorCode
    );
    console.log('whffu');
    console.log(markerColor);
    myMarkerImg = markerColor[0].img;
  }

  // console.log('마커 색깔', markerColor[0]);

  // overlay Open
  const openOverlay = (map, overlay) => {
    return function () {
      overlay.setMap(map);
    };
  };

  // 유저가 장소 마커 등록했을 때만 표시하게.
  useEffect(() => {
    if (userSelectMarkers.length > 0) {
      console.log('너는 마커');
      const pos = userSelectMarkers.length - 1;
      console.log('너는 마커', userSelectMarkers.length);

      const imageSrc = myMarkerImg;
      const imageSize = new kakao.maps.Size(40, 40);
      const imageOption = { offset: new kakao.maps.Point(15, 40) };

      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      const marker = new kakao.maps.Marker({
        map: existMap,
        position: new kakao.maps.LatLng(
          userSelectMarkers[pos].y,
          userSelectMarkers[pos].x
        ),
        image: markerImage,
      });

      console.log('원래 마커임', marker);
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
      // 커스텀 오버레이 제목 박스
      const titleContainer = document.createElement('div');
      titleContainer.classList.add('title_container');
      content.appendChild(titleContainer);
      // 커스텀 오버레이 제목 글자
      const markerTitle = document.createElement('div');
      markerTitle.classList.add('overlay__title');
      markerTitle.appendChild(
        document.createTextNode(userSelectMarkers[pos].title)
      );
      titleContainer.appendChild(markerTitle);
      // 커스텀 오버레이 닫기
      const closeBtn = document.createElement('button');
      closeBtn.classList.add('overlay__close');
      closeBtn.appendChild(document.createTextNode('X'));
      closeBtn.onclick = function () {
        overlay.setMap(null);
      };
      titleContainer.appendChild(closeBtn);
      // 커스텀 오버레이 카테고리
      const CategoryName = document.createElement('div');
      CategoryName.classList.add('category_name');
      CategoryName.appendChild(
        document.createTextNode([userSelectMarkers[pos].categoryName])
      );
      content.appendChild(CategoryName);
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
    }
  }, [userSelectMarkers, category]);

  useEffect(() => {
    // 처음에 생성했을때 룸마커가 비어있기에 필터링이안됨
    // 지금 문제점은 그냥 처음에 생성했을때 다 문제가 됨.
    // && userSelectMarkers.length > 0

    if (roomMarkers.length > 0) {
      console.log('마커필터링');

      // 선택한 마커 정보만 따로 빼기.
      const roomMarker = roomMarkers.filter(
        roomMarker => roomMarker.id === removeMarker.id
      );

      setRoomMarkers(roomMarkers.filter(room => room.id !== roomMarker[0].id));

      roomMarker[0].marker.setMap(null);

      setUserSelectMarker(
        userSelectMarkers.filter(
          usermarker => usermarker.id !== removeMarker.id
        )
      );

      setPublishMarkerFlag([...publishMarkerFlag, 1]);
    }
  }, [removeMarker]);

  return (
    <div className='user_marker'>
      <div></div>
    </div>
  );
}

export default Marker;
