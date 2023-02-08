import React from 'react';

const { kakao } = window;

// 중심좌표 기준으로 일치하는 것을 생성시킴.
// 맵 이동해서 중심좌표 바꾸니까 거기에 맞게 형성됨...
// 즉 이 컴포넌트 자체를 호출 할 때 컴포넌트를 클릭하면 해당 카테고리를 정보로 넘기고
function ControlBox(props) {
  const existMap = props.map;
  const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  // 장소 검색 객체를 생성합니다
  const ps = new kakao.maps.services.Places(existMap);

  const markerClick = (place, marker) => {
    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
    infowindow.setContent(
      `<div style="padding:5px;font-size:12px;">'${place.place_name}'</div>`
    );
    infowindow.open(existMap, marker);
  };
  // 지도에 마커를 표시하는 함수입니다
  function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    const marker = new kakao.maps.Marker({
      map: existMap,
      position: new kakao.maps.LatLng(place.y, place.x),
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', markerClick(place, marker));
  }

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      for (let i = 0; i < data.length; i += 1) {
        displayMarker(data[i]);
      }
    }
  }
  // 카테고리로 은행을 검색합니다
  ps.categorySearch('BK9', placesSearchCB, { useMapBounds: true });
  return (
    <div>
      <div></div>
    </div>
  );
}

export default ControlBox;
