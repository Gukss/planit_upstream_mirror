import React, { useState } from 'react';
import classes from './ScheduleStorage.module.scss';
import PlaceBoxItem from '../place/PlaceBoxItem';
// import { userMarkers } from '../../../../app/store';

function ScheduleStorage(props) {
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

export default ScheduleStorage;
