import { useState } from 'react';
import DatePicker from 'react-datepicker';
import { useRecoilState } from 'recoil';
import 'react-datepicker/dist/react-datepicker.css';
import { dateRangeState } from '../../../app/store';

import logoImg from '../../../app/assets/images/naver_login.png';

import Header from '../../../common/header/Header';

import classes from './CreateRoom.module.scss';

function CreateRoom() {
  const [dateRange, setDateRange] = useRecoilState(dateRangeState);
  const [startD, setStartDate] = useState(dateRange.startDate);
  const [endD, setEndDate] = useState(dateRange.endDate);

  const handleChange = dates => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
    console.log(start);
    console.log(end);
    setDateRange({ startDate: start, endDate: end });
  };
  return (
    <div>
      <Header />
      <div className={classes.create}>
        <div className={classes.create__title}>
          <div className={classes.create_title_text}>
            <div>PLAN</div>
            <div>!T</div>
          </div>
          <div className={classes.create_title_img}>
            <img src={logoImg} alt='' />
          </div>
        </div>
        <form>
          <div className={classes.create__main}>
            <p>여행일자</p>
            <div className={classes.create_date_wrap}>
              <DatePicker
                selected={startD}
                onChange={handleChange}
                selectsStart
                startDate={startD}
                endDate={endD}
                dateFormat='yyyy. MM. dd'
                selectsRange
                className={classes.datepicker}
              />
            </div>
            <p>여행 일정 제목</p>
            <div className={classes.create_main_id}>
              <input className={classes.input} type='text' />
            </div>
            <button className={classes.create_main_button} type='submit'>
              가입하기
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default CreateRoom;
