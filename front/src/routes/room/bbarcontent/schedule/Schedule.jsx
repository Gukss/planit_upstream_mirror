import React from 'react';
import { useRecoilValue } from 'recoil';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Schedule.module.scss';
import PlaceBox from '../place/PlaceBox';
import { userMarkers } from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule() {
  const items = useRecoilValue(userMarkers);

  return (
    <Bbar>
      <div className={classes.schedule_title}>
        <div className={classes.title_title}>일정</div>
        <p>
          원하는 날짜에 장소를 드래그하여 일정을 조정할 수 있습니다. 날짜 별
          장소는 같은 색으로 지도에 표기됩니다<div className=''></div>
        </p>
      </div>
      <PlaceBox boxTitle='보관함' items={items} />
      <br />
      <hr />
      <div className={classes.schedulebox_section}>
        <br />
        {/* {boxTitles.map(boxTitle => {
          return <PlaceBox boxTitle={boxTitle} />;
        })} */}
        {/* {items.map(item => {
          if (item.category === 'AD5') {
            return <PlaceBox boxTitle='숙소' item={item} />;
          }
        })} */}

        <ScheduleBox boxDate='2023.01.11' items={items} />
        <ScheduleBox boxDate='2023.01.12' items={items} />
        <ScheduleBox boxDate='2023.01.13' items={items} />
      </div>
    </Bbar>
  );
}

export default Schedule;
