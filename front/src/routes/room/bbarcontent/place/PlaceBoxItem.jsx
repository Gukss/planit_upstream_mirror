import React, { useState, useEffect } from 'react';
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
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const setMarkerPosition = useSetRecoilState(searchedPlace);
  const setRemoveMarker = useSetRecoilState(removeInformation);
  const [isClick, setIsClick] = useState(props.item.isConfirmed);
  const [publishMarkerFlag, setPublishMarkerFlag] = useRecoilState(markerFlag);
  const [confirmedChanged, setConfirmedChanged] =
    useRecoilState(isConfirmedChanged);

  // tag 클릭시 이동
  const moveMarker = () => {
    setMarkerPosition({
      x: props.item.x,
      y: props.item.y,
    });
  };

  const removeMarker = () => {
    setRemoveMarker(props.item);
    setMarkers(markers.filter(marker => marker.id !== props.item.id));
  };

  const onConfirmPlace = () => {
    // console.log('확정 버튼 누름', e);
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
    </div>
  );
}

export default PlaceBoxItem;
