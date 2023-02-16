// import React, { useEffect, useState } from 'react';
// import { useRecoilValue, useRecoilState } from 'recoil';
// import {
//   socketMarkers,
//   removeInformation,
//   userMarkers,
// } from '../../../../app/store';
// import pinkMarker from '../../../../app/assets/images/marker_pink.png';
// import redMarker from '../../../../app/assets/images/marker_red.png';
// import purpleMarker from '../../../../app/assets/images/marker_purple.png';
// import greenMarker from '../../../../app/assets/images/marker_green.png';
// import emeraldMarker from '../../../../app/assets/images/marker_emerald.png';
// import blueMarker from '../../../../app/assets/images/marker_blue.png';

// const { kakao } = window;

// function SocketMarker(props) {
//   const existMap = props.map;
//   const [roomMarkers, setRoomMarkers] = useState([]);
//   const [socketMarker, setSocketMarker] = useRecoilState(socketMarkers);
//   const [removeMarker, setRemoveMarker] = useRecoilState(removeInformation);
//   const [userMarker, setUserMarker] = useRecoilState(userMarkers);
//   const userColor = '#8059D1';

//   const colorList = [
//     { color: '#EB5252', img: redMarker },
//     { color: '#7997FE', img: blueMarker },
//     { color: '#90CE0A', img: greenMarker },
//     { color: '#61D9C3', img: emeraldMarker },
//     { color: '#8059D1', img: purpleMarker },
//     { color: '#FF7BBA	', img: pinkMarker },
//   ];

//   const markerColor = colorList.filter(item => item.color === userColor);

//   const myMarkerImg = markerColor[0].img;

//   // overlay Open
//   const openOverlay = (map, overlay) => {
//     return function () {
//       overlay.setMap(map);
//     };
//   };

//   // useEffect(() => {
//   //   console.log('소켓 마커임 ver2');
//   //   if (socketMarker.length > 0) {
//   //     setUserMarker(socketMarker);
//   //   }
//   // }, [socketMarker]);

//   useEffect(() => {
//     if (socketMarker.length > 0) {
//       const pos = socketMarker.length - 1;
//       console.log('소켓 마커임');

//       const imageSrc = myMarkerImg;
//       const imageSize = new kakao.maps.Size(40, 40);
//       const imageOption = { offset: new kakao.maps.Point(15, 40) };

//       const markerImage = new kakao.maps.MarkerImage(
//         imageSrc,
//         imageSize,
//         imageOption
//       );

//       const marker = new kakao.maps.Marker({
//         map: existMap,
//         position: new kakao.maps.LatLng(
//           socketMarker[pos].y,
//           socketMarker[pos].x
//         ),
//         image: markerImage,
//       });

//       marker.setMap(existMap);

//       setRoomMarkers([
//         ...roomMarkers,
//         {
//           id: socketMarker[pos].id,
//           marker,
//         },
//       ]);

//       const overlay = new kakao.maps.CustomOverlay({
//         map: existMap,
//         position: marker.getPosition(),
//       });

//       overlay.setMap(null); // 처음에 커스텀오버레이 안보여주기 위함

//       // 최상단 div 커스텀 오버레이
//       const content = document.createElement('div');
//       content.classList.add('overlay');
//       // 커스텀 오버레이 제목 박스
//       const titleContainer = document.createElement('div');
//       titleContainer.classList.add('title_container');
//       content.appendChild(titleContainer);
//       // 커스텀 오버레이 제목 글자
//       const markerTitle = document.createElement('div');
//       markerTitle.classList.add('overlay__title');
//       markerTitle.appendChild(document.createTextNode(socketMarker[pos].title));
//       titleContainer.appendChild(markerTitle);
//       // 커스텀 오버레이 닫기
//       const closeBtn = document.createElement('button');
//       closeBtn.classList.add('overlay__close');
//       closeBtn.appendChild(document.createTextNode('X'));
//       closeBtn.onclick = function () {
//         overlay.setMap(null);
//       };
//       titleContainer.appendChild(closeBtn);
//       // 커스텀 오버레이 카테고리
//       const CategoryName = document.createElement('div');
//       CategoryName.classList.add('category_name');
//       CategoryName.appendChild(
//         document.createTextNode([socketMarker[pos].categoryName])
//       );
//       content.appendChild(CategoryName);
//       // 이용자 마커 제거
//       const markerRemove = document.createElement('button');
//       markerRemove.classList.add('marker_remove');
//       markerRemove.appendChild(document.createTextNode('장소 제거 -'));
//       markerRemove.onclick = function () {
//         // recoilstate에서 해당 마커 지우기.
//         overlay.setMap(null);
//         marker.setMap(null);
//         const removeInfo = {
//           id: socketMarker[pos].id,
//           y: socketMarker[pos].y,
//           x: socketMarker[pos].x,
//         };
//         setRemoveMarker(removeInfo);
//       };
//       content.appendChild(markerRemove);
//       overlay.setContent(content);

//       // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
//       kakao.maps.event.addListener(
//         marker,
//         'click',
//         openOverlay(existMap, overlay)
//       );
//     }
//   }, [socketMarker]);

//   useEffect(() => {
//     // 처음에 생성했을때 룸마커가 비어있기에 필터링이안됨
//     // 지금 문제점은 그냥 처음에 생성했을때 다 문제가 됨.

//     if (roomMarkers.length > 0) {
//       console.log('마커필터링, 소켓');

//       // 선택한 마커 정보만 따로 빼기.
//       const roomMarker = roomMarkers.filter(
//         roomMarker => roomMarker.id === removeMarker.id
//       );

//       setRoomMarkers(roomMarkers.filter(room => room.id !== roomMarker[0].id));

//       roomMarker[0].marker.setMap(null);
//       // 그러면 마커 정보를 다시 -> ;; 없애야함

//       setSocketMarker(
//         socketMarker.filter(usermarker => usermarker.id !== removeMarker.id)
//       );
//     }
//   }, [removeMarker]);

//   return <div></div>;
// }

// export default SocketMarker;
