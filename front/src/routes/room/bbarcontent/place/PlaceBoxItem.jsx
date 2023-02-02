import React from 'react';
import classes from './PlaceBoxItem.module.scss';

function PlaceBoxItem() {
  const onClickHandler = () => {
    console.log('클릭됐다!');
  };
  return (
    <div
      className={classes.item_body}
      onClick={onClickHandler}
      onKeyDown={onClickHandler}
      role='button'
      tabIndex={0}
    >
      <div className={classes.item_tag}></div>
      <p className={classes.item_title}>성심당</p>
    </div>
  );
}

export default PlaceBoxItem;
