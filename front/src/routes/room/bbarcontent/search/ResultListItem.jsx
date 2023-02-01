import React from 'react';

import classes from './ResultListItem.module.scss';

const items = [1, 2, 3];

function ResultListItem(props) {
  const test = props.place.category_name;
  const testlist = test.split('>');
  console.log(testlist);
  const ohyeah = testlist[testlist.length - 1];
  console.log(ohyeah);
  const detailUrl = props.place.place_url;

  return (
    <div className={classes.resultitem}>
      <div className={classes.resultitem__title}>
        <div className={classes.title_namecategory}>
          <div className={classes.title_name}>{props.place.place_name}</div>
          <div className={classes.title_category}>{ohyeah}</div>
        </div>
        <i className='bx bx-plus'></i>
      </div>
      <div className={classes.resultitem__content}>
        <div>{props.place.road_address_name}</div>
        <div className={classes.content_easyaddress}>
          (지번) {props.place.address_name}
        </div>
        <div className={classes.content_phone}>
          <i className='bx bxs-phone'></i>
          <p>{props.place.phone}</p>
        </div>
        <a href={detailUrl} target='_blank' rel='noopener noreferrer'>
          가게 정보 확인하기
        </a>
      </div>
      {/* <div className={classes.resultitem__photo}>
        {items.map(item => (
          <div className={classes.resultitem__photo__item} />
        ))}
      </div> */}
    </div>
  );
}

export default ResultListItem;
