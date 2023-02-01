import React, { useEffect, useRef } from 'react';
import './Map.scss';

const { kakao } = window;
const title = '카카오 스페이스';
const markerPositions = [
  {
    content: `
      <div class='wrap'> <div class='info'>
              <div class='title'>
                  ${title}
                  <button class="close" onClick={} >x</ button>
              </div>
              <div class="body">
                  <div class="img">
                      <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">
                 </div>
                  <div class="desc">
                      <div class="ellipsis">제주특별시 제주시 첨단로 242</div>
                      <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>
                      <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>
                  </div>
              </div>
          </div>
      </div>`,

    latlng: new kakao.maps.LatLng(33.450705, 126.570677),
  },
  {
    content: "<div class='title'>생태연못</div>",
    latlng: new kakao.maps.LatLng(33.450936, 126.569477),
  },
  {
    content: '<div>텃밭</div>',
    latlng: new kakao.maps.LatLng(33.450879, 126.56994),
  },
  {
    content: '<div>근린공원</div>',
    latlng: new kakao.maps.LatLng(33.451393, 126.570738),
  },
];

function makeOverListener(map, marker, infowindow) {
  return function () {
    infowindow.open(map, marker);
  };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
  return function () {
    infowindow.close();
  };
}

// 창닫기
function closeOverlay(infowindow) {
  infowindow.close();
}

function Map() {
  const mapContainer = useRef(null);
  const position = new kakao.maps.LatLng(33.450701, 126.570667);
  // const markerPosition = new kakao.maps.LatLng(33.440601, 126.570666);

  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };

  useEffect(() => {
    const map = new kakao.maps.Map(mapContainer.current, mapOptions);
    // const marker = new kakao.maps.Marker({ position: markerPosition }); // 마커 생성
    // marker.setMap(map);
    for (let i = 0; i < markerPositions.length; i += 1) {
      const marker = new kakao.maps.Marker({
        map, // 마커를 표시할 지도
        position: markerPositions[i].latlng, // 마커의 위치
      });
      marker.setMap(map);
      const infowindow = new kakao.maps.InfoWindow({
        content: markerPositions[i].content, // 인포윈도우에 표시할 내용
      });

      // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
      // 이벤트 리스너로는 클로저를 만들어 등록합니다
      // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
      kakao.maps.event.addListener(
        marker,
        'click',
        makeOverListener(map, marker, infowindow)
      );
      // kakao.maps.event.addListener(
      //   marker,
      //   'mouseout',
      //   makeOutListener(infowindow)
      // );
    }
  }, []);

  return (
    <div className='map' ref={mapContainer}>
      <div></div>
    </div>
  );
}

export default Map;

// import React, { useEffect, useRef } from 'react';
// import './Map.scss';

// const { kakao } = window;
// const title = '제목';
// const markerPositions = [
//   {
//     content: `<div class='title'>${title}</div>`,
//     latlng: new kakao.maps.LatLng(33.450936, 126.569477),
//   },
// ];

// function Map() {
//   const mapContainer = useRef(null);
//   const position = new kakao.maps.LatLng(33.450701, 126.570667);
//   const mapOptions = {
//     center: position, // 지도의 중심좌표
//     level: 4, // 지도의 확대 레벨
//   };

//   useEffect(() => {
//     const map = new kakao.maps.Map(mapContainer.current, mapOptions);

//     for (let i = 0; i < markerPositions.length; i += 1) {
//       const overlay = new kakao.maps.CustomOverlay({
//         content : markerPositions[i].latlng
//         map: map,
//         position: marker.getPosition(),
//       });
//     }
//   }, []);

//   return (
//     <div className='map' ref={mapContainer}>
//       <div></div>
//     </div>
//   );
// }

// export default Map;
