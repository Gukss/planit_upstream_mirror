import React, { useState, useRef } from 'react';
import classes from './ScheduleBoxItem.module.scss';

function ScheduleBoxItem(props) {
  return (
    <div draggable className={classes.item_body}>
      <div className={classes.item_tag}></div>
      <div className={classes.item_title}>
        <h3>{props.item.title}</h3>
      </div>
    </div>
  );
}

export default ScheduleBoxItem;
