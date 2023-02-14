import React from 'react';
import { useRecoilState, useRecoilValue, useSetRecoilState } from 'recoil';
import {
  searchedPlace,
  userMarkers,
  currentMarker,
  markerFlag,
  roomInfoState,
} from '../../../../app/store';

import classes from './ResultListItem.module.scss';

function ResultListItem(props) {
  const roomInfo = useRecoilValue(roomInfoState);
  const userMarkerColor = roomInfo.colorCode;

  const fullCategory = props.place.category_name;
  const fullCategoryList = fullCategory.split('>');
  console.log('ResultItem 생성마다 호출');
  const categoryKeyword = fullCategoryList[fullCategoryList.length - 1];
  const detailUrl = props.place.place_url;

  const setAddMarker = useSetRecoilState(currentMarker);
  const setSelectMarker = useSetRecoilState(searchedPlace);
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const [publishMarkerFlag, setPublishMarkerFlag] = useRecoilState(markerFlag);

  // 클릭시 좌표값 저장후 이동.
  const onClickHandler = e => {
    setSelectMarker({
      x: props.place.x,
      y: props.place.y,
    });
  };

  const onAddPlace = e => {
    console.log('장소 추가 버튼 누름');
    // 장소 추가하면 해줄 작업
    // 전역 state인 searchedPlaces를 업데이트할 함수 setSearchedPlaces
    // 전역 state에서 가져온 markers에 추가하려는 장소가 이미 있다면?

    console.log(markers);

    const newMarker = {
      id: props.place.id,
      categoryCode: props.place.category_group_code,
      categoryName: props.place.category_group_name,
      colorCode: userMarkerColor,
      isConfirmed: false,
      title: props.place.place_name,
      x: props.place.x,
      y: props.place.y,
    };

    const check = markers.findIndex(marker => marker.id === props.place.id);
    if (check === -1) {
      setAddMarker(newMarker);
      setMarkers([...markers, newMarker]);
      setPublishMarkerFlag([...publishMarkerFlag, 1]);
    }
  };

  return (
    <div
      className={classes.resultitem}
      onClick={onClickHandler}
      onKeyDown={onClickHandler}
      role='button'
      tabIndex={0}
    >
      {/* <div className={classes.resultitem}> */}
      <div className={classes.resultitem__title}>
        <div className={classes.title_namecategory}>
          <div className={classes.title_name}>
            {props.index + 1}. {props.place.place_name}
          </div>
          <div className={classes.title_category}>{categoryKeyword}</div>
        </div>
        <div
          className={classes.title_plus}
          onClick={onAddPlace}
          onKeyDown={onAddPlace}
          role='button'
          tabIndex={0}
        >
          <i className='bx bx-plus' />
        </div>
      </div>
      <div className={classes.resultitem__content}>
        <div>{props.place.road_address_name}</div>
        <div className={classes.content_easyaddress}>
          (지번) {props.place.address_name}
        </div>
        {props.place.phone && (
          <div className={classes.content_phone}>
            <i className='bx bxs-phone'></i>
            <p>{props.place.phone}</p>
          </div>
        )}
        <div>
          <a href={detailUrl} target='_blank' rel='noopener noreferrer'>
            <i className='bx bxs-map' style={{ color: '#464545' }}></i>가게 정보
            확인하기
          </a>
        </div>
      </div>
    </div>
  );
}

export default ResultListItem;
