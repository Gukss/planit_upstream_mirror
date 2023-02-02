import React from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Place.module.scss';
import PlaceBox from './PlaceBox';

function Place() {
  const boxTitles = ['숙소', '식당', '관광지', '카페'];
  return (
    <Bbar>
      <div className={classes.place_title}>
        <div className={classes.title_title}>장소 보관함</div>
        <p>
          가고 싶은 장소를 담고 장소를 클릭하여 일정에 들어갈 확정 장소를
          선택하세요!
        </p>
      </div>
      <div className={classes.placebox_section}>
        {boxTitles.map(boxTitle => {
          return <PlaceBox boxTitle={boxTitle} />;
        })}
      </div>
    </Bbar>
  );
}

export default Place;
