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
      <div className={classes.background_img}>
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
        </div>
        <div className={classes.main}>
          <div className={classes.main_section}>
            <div className={classes.main_container}>
              <div className={classes.row}>
                <div className={classes.col}>
                  <div className={classes.col_section}>
                    <p>
                      <span>여행 정보 </span>
                      <span>친구 초대</span>
                    </p>
                    <input
                      className={classes.checkbox}
                      type='checkbox'
                      id='reg-log'
                      name='reg-log'
                    />
                    <label htmlFor='reg-log'></label>
                    <div className={classes.card3d_wrap}>
                      <div className={classes.card3d_wrapper}>
                        <div className={classes.card_front}>
                          <div className={classes.center_wrap}>
                            <div className={classes.center_section}>
                              <span>여행일자</span>
                              <div className={classes.date_wrap}>
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
                              <span>여행일정제목</span>
                              <div className={classes.form_group}>
                                <input
                                  className={classes.form_style}
                                  placeholder='이번 여행을 한마디로 표현하자면?'
                                />
                              </div>
                              <button
                                className={classes.main__button}
                                type='submit'
                              >
                                방 생성하기
                              </button>
                            </div>
                          </div>
                        </div>
                        <div className={classes.card_back}>
                          <div className={classes.center_wrap}>
                            <div className={classes.center_section}>
                              <span>친구초대</span>
                              <div className={classes.form_group}>
                                <input
                                  className={classes.form_style}
                                  placeholder='아이디를 입력해주세요'
                                />
                              </div>
                              <button
                                className={classes.main__button}
                                type='submit'
                              >
                                방 생성하기
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateRoom;
