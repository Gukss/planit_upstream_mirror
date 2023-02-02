import React from 'react';
import classes from './PlaceBox.module.scss';
import PlaceBoxItem from './PlaceBoxItem';

function PlaceBox(props) {
  return (
    <div>
      <div className={classes.box_title}>{props.boxTitle}</div>
      <div className={classes.box_content}>
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
        <PlaceBoxItem />
      </div>
    </div>
  );
}

export default PlaceBox;
