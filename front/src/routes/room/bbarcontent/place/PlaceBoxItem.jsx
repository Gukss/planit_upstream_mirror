import { useRecoilState } from 'recoil';
import React, { useState, useRef } from 'react';
import classes from './PlaceBoxItem.module.scss';
import { userMarkers } from '../../../../app/store';

function PlaceBoxItem(props) {
  const [markers, setMarkers] = useRecoilState(userMarkers);
  const [isClick, setIsClick] = useState(props.item.isConfirmed);
  console.log(props.item.isConfirmed);

  const onConfirmPlace = e => {
    console.log('확정 버튼 누름', e.target);

    setMarkers(
      markers.map(it =>
        it.title === e.target.innerText
          ? { ...it, isConfirmed: !it.isConfirmed }
          : it
      )
    );

    console.log('선택', props.isConfirmed);
    setIsClick(props.isConfirmed);
    console.log('컨펌 후 마커', markers);
  };
  return (
    <div
      className={classes.item_body}
      onClick={onConfirmPlace}
      onKeyDown={onConfirmPlace}
      role='button'
      tabIndex={0}
    >
      <div
        className={classes.item_tag}
        style={{ backgroundColor: `${props.item.userColor}` }}
      ></div>
      <div
        className={
          props.item.isConfirmed ? classes.item_title_click : classes.item_title
        }
      >
        <p>{props.item.title}</p>
      </div>
    </div>
  );
}

export default PlaceBoxItem;
