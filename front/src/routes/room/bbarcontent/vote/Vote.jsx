import { useRecoilValue } from 'recoil';
import React, { useState } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Vote.module.scss';
// import PlaceBox from './PlaceBox';
import { userMarkers } from '../../../../app/store';

function Vote() {
  return (
    <Bbar>
      <div className={classes.vote_title}>
        <div className={classes.title_title}>투표</div>
        <p>투표를 생성하여 원하는 대로 투표 진행해보세요.</p>
      </div>
      <div className={classes.vote_section}>
        {/* {boxTitles.map(boxTitle => {
          return <PlaceBox boxTitle={boxTitle} />;
        })} */}
        {/* {items.map(item => {
          if (item.category === 'AD5') {
            return <PlaceBox boxTitle='숙소' item={item} />;
          }
        })} */}
        {/* <PlaceBox boxTitle='숙소' items={itemHotels} />
        <PlaceBox boxTitle='음식점' items={itemRestaurants} />
        <PlaceBox boxTitle='관광지' items={itemSpots} />
        <PlaceBox boxTitle='카페' items={itemCafes} /> */}
      </div>
    </Bbar>
  );
}

export default Vote;
