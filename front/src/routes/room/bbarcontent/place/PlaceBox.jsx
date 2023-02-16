import React, { useState } from 'react';
import classes from './PlaceBox.module.scss';
import PlaceBoxItem from './PlaceBoxItem';
// import { userMarkers } from '../../../../app/store';

function PlaceBox(props) {
  // const items = useRecoilValue(userMarkers);

  // let [itemActive, setItemActive] = useState('');

  // const toggleActive = e => {
  //   setItemActive(prev => {
  //     return e.target.value;
  //   });

  //   // if (hasClass) {
  //   //   bg.classList.remove(classClicked);
  //   // } else {
  //   //   bg.classList.add(classClicked);
  //   // }
  // };
  // console.log('placebox에 옴', props);

  return (
    <div>
      <div className={classes.box_title}>{props.boxTitle}</div>
      <div className={classes.box_content}>
        {props.items.length ? (
          props.items.map(item => {
            return <PlaceBoxItem item={item} />;
          })
        ) : (
          <div className={classes.box_content_empty}>
            <p>저장한 장소가 없습니다</p>
          </div>
        )}
        {/* {props.items.map(item => {
          return <PlaceBoxItem item={item} />;
        })} */}
      </div>
    </div>
  );
}

export default PlaceBox;
