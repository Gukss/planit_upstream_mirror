import React, { useState } from 'react';
import classes from './ScheduleBox.module.scss';
import ScheduleBoxItem from './ScheduleBoxItem';

function ScheduleBox(props) {
  return (
    <div>
      <div className={classes.box_content}>
        <p>{props.boxDate}</p>
        {props.items.map(item => {
          return <ScheduleBoxItem item={item} />;
        })}
      </div>
    </div>
  );
}

export default ScheduleBox;
