import React, { useEffect } from 'react';
import { useRecoilValue, useSetRecoilState, useRecoilState } from 'recoil';
import {
  currentMarker,
  userMarkers,
  categoryCheck,
  markerFlag,
  roomInfoState,
} from '../../../../app/store';

const { kakao } = window;

let categorySearchMarkers = [];

// 커스텀오버레이 열기
const openOverlay = (map, overlay) => {
  return function () {
    overlay.setMap(map);
  };
};

const deleteMarker = () => {
  for (let i = 0; i < categorySearchMarkers.length; i += 1) {
    categorySearchMarkers[i].setMap(null);
  }
  categorySearchMarkers = [];
};

// 중심좌표 기준으로 일치하는 것을 생성시킴.
// 맵 이동해서 중심좌표 바꾸니까 거기에 맞게 형성됨...
// 즉 이 컴포넌트 자체를 호출 할 때 컴포넌트를 클릭하면 해당 카테고리를 정보로 넘기고
function CategoryMarker(props) {
  const roomInfo = useRecoilValue(roomInfoState);
  const userMarkerColor = roomInfo.colorCode;
  const existMap = props.map;
  const categoryValue = useRecoilValue(categoryCheck);
  const [publishMarkerFlag, setPublishMarkerFlag] = useRecoilState(markerFlag);
  const addSetMarker = useSetRecoilState(currentMarker); // 지금 선택한 마커 정보
  const setSelectMarkers = useSetRecoilState(userMarkers); // 유저가 선택한 마커 모음

  useEffect(() => {
    // 카테고리 검색 결과 초기화
    deleteMarker();

    // 장소 검색 객체를 생성합니다
    const ps = new kakao.maps.services.Places(existMap);

    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place) {
      const imageSrc = categoryValue.imageUrl; // 마커이미지의 주소입니다
      const imageSize = new kakao.maps.Size(40, 40); // 마커이미지의 크기입니다
      // const imageOption = { offset: new kakao.maps.Point(20, 30) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize
        // imageOption
      );

      // 마커를 생성하고 지도에 표시합니다
      const marker = new kakao.maps.Marker({
        map: existMap,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage,
      });

      // setRemoveAtSearch(...removeAtSearch, marker);
      categorySearchMarkers.push(marker);

      const overlay = new kakao.maps.CustomOverlay({
        map: existMap,
        position: marker.getPosition(),
      });

      overlay.setMap(null);

      const content = document.createElement('div');
      content.classList.add('overlay');
      // 커스텀 오버레이 제목 박스
      const titleContainer = document.createElement('div');
      titleContainer.classList.add('title_container');
      content.appendChild(titleContainer);
      // 커스텀 오버레이 제목 글자
      const markerTitle = document.createElement('div');
      markerTitle.classList.add('overlay__title');
      markerTitle.appendChild(document.createTextNode(place.place_name));
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
        document.createTextNode([place.category_group_name])
      );
      content.appendChild(CategoryName);
      // 커스텀 오버레이로 이용자 마커 등록
      const markerAdd = document.createElement('button');
      markerAdd.classList.add('marker_add');
      markerAdd.appendChild(document.createTextNode('장소 추가 +'));
      // 유저 선택 마커 추가
      markerAdd.onclick = function () {
        console.log('usermarker');
        // 현재 클릭한 값 찾기.
        addSetMarker({
          id: place.id,
          title: place.place_name,
          x: place.x,
          y: place.y,
        });
        // userMarker 배열에 추가
        setSelectMarkers(selectMarkers => [
          ...selectMarkers,
          {
            id: place.id,
            categoryCode: place.category_group_code,
            categoryName: place.category_group_name,
            colorCode: userMarkerColor,
            isConfirmed: false,
            title: place.place_name,
            x: place.x,
            y: place.y,
          },
        ]);
        setPublishMarkerFlag([...publishMarkerFlag, 1]);
        overlay.setMap(null);
        marker.setMap(existMap);
        // setMarker
      };
      content.appendChild(markerAdd);
      overlay.setContent(content);

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(
        marker,
        'click',
        openOverlay(existMap, overlay)
      );
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
    ps.categorySearch(categoryValue.code, placesSearchCB, {
      useMapBounds: true,
    });
  }, [categoryValue]);

  return (
    <div>
      <div></div>
    </div>
  );
}

export default CategoryMarker;
