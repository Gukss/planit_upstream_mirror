import React from 'react';

import classes from './ResultListItem.module.scss';

const items = [1, 2, 3];

function ResultListItem(props) {
  return (
    <div className={classes.resultitem}>
      <div className={classes.resultitem__title}>{props.place.place_name}</div>
      <div className={classes.resultitem__content}>
        <div>별점4.5</div>
        <div>리뷰 500</div>
        <div>ㅇㅇ시 ㅇㅇ구 ㅇㅇ동 1000</div>
        <div>{props.place.place_url}</div>
      </div>
      <div className={classes.resultitem__photo}>
        {items.map(item => (
          <div className={classes.resultitem__photo__item} />
        ))}
      </div>
    </div>
  );
}

export default ResultListItem;
