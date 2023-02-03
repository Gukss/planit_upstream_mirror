import React from 'react';
import classes from './CamListItem.module.scss';

function CamListItem(props) {
  // 고민중인것 : cam-size를 계속해서 고정했다가 일정 이하로 안보이게 하게 짤까?
  return (
    <div className={classes.camListItem}>
      <div className={classes.camListItem__text}>유저 : {props.user}</div>
    </div>
  );
}

export default CamListItem;
