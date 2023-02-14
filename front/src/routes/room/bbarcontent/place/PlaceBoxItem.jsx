import React, { useState, useEffect, useRef } from 'react';
import { useRecoilState, useSetRecoilState } from 'recoil';
import classes from './PlaceBoxItem.module.scss';
import {
  userMarkers,
  searchedPlace,
  removeInformation,
  isConfirmedChanged,
  markerFlag,
} from '../../../../app/store';

function PlaceBoxItem(props) {
  // const [removeMar, setRemoveMarker] = useRecoilState(removeInformation);
  const setRemoveMarker = useSetRecoilState(removeInformation);
  const [markerPosition, setMarkerPosition] = useRecoilState(searchedPlace);
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const [isClick, setIsClick] = useState(props.item.isConfirmed);
  const [publishMarkerFlag, setPublishMarkerFlag] = useRecoilState(markerFlag);
  const [confirmedChanged, setConfirmedChanged] =
    useRecoilState(isConfirmedChanged);

  // tag 클릭시 이동
  const moveMarker = e => {
    setMarkerPosition({
      x: props.item.x,
      y: props.item.y,
    });
  };

  const removeMarker = e => {
    // console.log('제거버튼', props.item);
    setRemoveMarker(props.item);
    setMarkers(markers.filter(marker => marker.id !== props.item.id));
  };

  const onConfirmPlace = e => {
    console.log('확정 버튼 누름', e);
    setConfirmedChanged(true);

    setMarkers(
      markers.map(marker =>
        marker.id === props.item.id
          ? { ...marker, isConfirmed: !marker.isConfirmed }
          : marker
      )
    );

    setIsClick(props.isConfirmed);
    setPublishMarkerFlag([...publishMarkerFlag, 1]);
    console.log('컨펌 후 마커', markers);
  };
  return (
    <div className={classes.item_body}>
      <div
        className={classes.item_tag}
        style={{ backgroundColor: `${props.item.colorCode}` }}
        onClick={moveMarker}
        onKeyDown={moveMarker}
        role='button'
        aria-label='move_marker'
        tabIndex={0}
      ></div>
      <div
        className={
          props.item.isConfirmed ? classes.item_box_click : classes.item_box
        }
      >
        <div
          className={classes.item_title}
          onClick={onConfirmPlace}
          onKeyDown={onConfirmPlace}
          role='button'
          tabIndex={0}
        >
          {props.item.title}
        </div>
        <button onClick={removeMarker}>
          <i className='bx bx-x' />
        </button>
      </div>
      {/* <div className={classes.hover_box}>
        <button>이동</button>
        <button>확정</button>
      </div> */}
    </div>
  );
}

export default PlaceBoxItem;
