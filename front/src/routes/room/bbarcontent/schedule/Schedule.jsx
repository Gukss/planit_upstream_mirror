import React from 'react';
import { useRecoilValue } from 'recoil';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Schedule.module.scss';
import PlaceBox from '../place/PlaceBox';
import { userMarkers } from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule() {
  const itemsRaw = useRecoilValue(userMarkers);
  const items = itemsRaw.filter(item => item.isConfirmed === true);

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
        <ScheduleBox boxDate='2023.날짜데이터넣어야' items={items} />
        <ScheduleBox boxDate='2023.01.12' items={items} />
        <ScheduleBox boxDate='2023.01.13' items={items} />
        {/* 근데 ScheduleBox는 정해진 개수가 아니라서 반복문으로 해야 한다 */}
      </div>
    </Bbar>
  );
}

export default Schedule;
