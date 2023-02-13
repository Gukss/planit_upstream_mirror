import { useRecoilValue } from 'recoil';
import React, { useEffect, useState } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Place.module.scss';
import PlaceBox from './PlaceBox';
import { userMarkers } from '../../../../app/store';

function Place() {
  const items = useRecoilValue(userMarkers);

  console.log('여기', items);

  const itemHotels = items.filter(item => item.categoryCode === 'AD5');
  const itemRestaurants = items.filter(item => item.categoryCode === 'FD6');
  const itemSpots = items.filter(item => item.categoryCode === 'AT4');
  const itemCafes = items.filter(item => item.categoryCode === 'CE7');
  const itemElses = items.filter(
    item =>
      item.categoryCode !== 'AD5' &&
      item.categoryCode !== 'FD6' &&
      item.categoryCode !== 'AT4' &&
      item.categoryCode !== 'CE7'
  );

  return (
    <Bbar>
      <div className={classes.place_title}>
        <div className={classes.title_title}>장소 보관함</div>
        <p>
          가고 싶은 장소를 담고 장소를 클릭하여 일정에 들어갈 확정 장소를
          선택하세요! 색깔을 눌러 해당 위치로 이동하세요!
        </p>
      </div>
      <div className={classes.placebox_section}>
        {/* {boxTitles.map(boxTitle => {
          return <PlaceBox boxTitle={boxTitle} />;
        })} */}
        {/* {items.map((item, index) => (
          // if (item.category === 'AD5') {
          <PlaceBox key={item} boxTitle='숙소' item={item} />
          // } else if (item.category === 'FD6') {
          //   return <PlaceBox boxTtile='음식점' item={item} />;
          // }
        ))} */}
        <PlaceBox boxTitle='숙소' items={itemHotels} />
        <PlaceBox boxTitle='음식점' items={itemRestaurants} />
        <PlaceBox boxTitle='관광지' items={itemSpots} />
        <PlaceBox boxTitle='카페' items={itemCafes} />
        <PlaceBox boxTitle='기타' items={itemElses} />
      </div>
    </Bbar>
  );
}

export default Place;
