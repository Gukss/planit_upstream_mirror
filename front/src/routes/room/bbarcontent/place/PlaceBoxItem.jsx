import { useRecoilState } from 'recoil';
import React, { useState, useRef } from 'react';
import classes from './PlaceBoxItem.module.scss';
import { userMarkers } from '../../../../app/store';

function PlaceBoxItem(props) {
  const [markers, setMarkers] = useRecoilState(userMarkers);

  const onConfirmPlace = e => {
    console.log('확정 버튼 누름', e.target.innerText);
    const targetMarker = markers.filter(
      marker => marker.title === e.target.innerText
    );
    console.log('마커', targetMarker);
  };
  return (
    <div
      className={classes.item_body}
      onClick={onConfirmPlace}
      onKeyDown={onConfirmPlace}
      role='button'
      tabIndex={0}
    >
      <div className={classes.item_tag}></div>
      <div className={classes.item_title}>
        <p>{props.item.title}</p>
      </div>
    </div>
  );
}

export default PlaceBoxItem;
