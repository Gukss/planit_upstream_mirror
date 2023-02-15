import DatePicker from 'react-datepicker';
import { useRecoilState } from 'recoil';
import { useEffect, useState } from 'react';
import axios from 'axios';
import 'react-datepicker/dist/react-datepicker.css';
import { useNavigate } from 'react-router-dom';
import FriendListItem from './friendbox/friendListItem';
import {
  dateRangeState,
  userInfoState,
  roomInfoState,
} from '../../../app/store';
import logoImg from '../../../app/assets/images/planit_logo_reverse.png';
import Header from '../../../common/header/Header';
import classes from './CreateRoom.module.scss';

function CreateRoom() {
  const [dateRange, setDateRange] = useRecoilState(dateRangeState);
  const [startD, setStartDate] = useState(dateRange.startDate);
  const [endD, setEndDate] = useState(dateRange.endDate);
  const [roomName, setRoomName] = useState('');
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [roomInfo, setRoomInfo] = useRecoilState(roomInfoState);
  const [inviteUserPk, setInviteUserPk] = useState([]);
  const navigate = useNavigate();

  // 친구 검색을 위한 dropdown 작업
  const [inputValue, setInputValue] = useState('');
  const [showDropdown, setShowDropdown] = useState(false);
  const [dropDownFriends, setDropDownFriends] = useState([]);
  const [selectFriends, setSelectFriends] = useState([]);

  // axios 인스턴스 기본값 설정
  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  // 룸 이름 실시간 확인
  const changeRoomName = e => {
    setRoomName(e.target.value);
  };

  // date 타입 변경
  const dateToString = date => {
    return `${date.getFullYear()}-${(date.getMonth() + 1)
      .toString()
      .padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
  };

  // 방 만들기 axios
  const createRoom = async e => {
    const requestData = {
      startDate: dateToString(startD),
      endDate: dateToString(endD),
      roomName: `${roomName}`,
    };
    const responseCreateRoom = await instance.post(`/rooms`, requestData);
    const makeInviteList = selectFriends.map(friend => ({
      receiverMemberId: friend.memberAppId,
      roomId: responseCreateRoom.data.roomId,
    }));
    console.log(makeInviteList);
    const responseInviteFriends = await instance.post(
      '/notification',
      makeInviteList
    );
    const reqMemRoomData = {
      roomId: responseCreateRoom.data.roomId,
      colorCode: '#EB5252',
    };
    const resMemberRoom = await instance.post('/rooms/users', reqMemRoomData);
    setRoomInfo({
      roomId: responseCreateRoom.data.roomId,
      startDate: dateToString(startD),
      endDate: dateToString(endD),
      roomName: `${roomName}`,
      colorCode: '#EB5252',
    });
    if (!roomInfo.roomId) {
      console.log(roomInfo);
      setTimeout(() => navigate('/room/search'), 600);
    } else {
      navigate('/room/search');
    }
  };

  // startDate, endDate 변경
  const handleChange = dates => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
    console.log(start);
    console.log(end);
    setDateRange({ startDate: start, endDate: end });
  };

  // 친구 검색 axios
  const checkFriend = async event => {
    const InputValue = inputValue;
    if (event !== '') {
      try {
        const response = await axios.get(
          `https://i8b202.p.ssafy.io/api/members/${event}`
        );
        console.log(response.data);
        setDropDownFriends(response.data);
        setShowDropdown(true);
      } catch (error) {
        console.log(error);
      }
    }
  };

  // 친구검색 input
  const handleInputChange = event => {
    setInputValue(event.target.value);
    checkFriend(event.target.value);
    console.log(1);
  };

  // 보낼 친구 저장
  const handleFriendClick = friend => {
    console.log(userInfo);
    setInputValue('');
    console.log(friend);
    if (selectFriends.length < 5) {
      setSelectFriends([...selectFriends, friend]);
    } else {
      alert('5명까지 초대가능합니다.');
    }
    setShowDropdown(false);
    // console.log(2);
  };

  // 보낼 리스트에서 삭제
  const handleRemoveClick = noF => {
    setSelectFriends(
      selectFriends.filter(selectFriend => selectFriend !== noF)
    );
    console.log(selectFriends);
  };

  const filteredfriends = dropDownFriends.filter(
    option =>
      !selectFriends.includes(option) &&
      userInfo.memberAppId !== option.memberAppId
  );

  return (
    <div>
      <Header />
      <div className={classes.background_img}>
        <div className={classes.create}>
          <div className={classes.create__title}>
            <div className={classes.create_title_text}>
              <div className={classes.create_title_img}>
                <img src={logoImg} alt='' />
              </div>
              PLAN!T
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
                                  dateFormat='yyyy-MM-dd'
                                  selectsRange
                                  className={classes.datepicker}
                                />
                              </div>
                              <span>여행일정제목</span>
                              <div className={classes.form_group}>
                                <input
                                  className={classes.form_style}
                                  placeholder='이번 여행을 한마디로 표현하자면?'
                                  onChange={changeRoomName}
                                />
                              </div>
                              <button
                                className={classes.main__button}
                                type='submit'
                                onClick={createRoom}
                              >
                                방 생성하기
                              </button>
                            </div>
                          </div>
                        </div>
                        <div className={classes.card_back}>
                          <div className={classes.center_wrap}>
                            <div className={classes.center_section}>
                              <div className={classes.friend_list}>
                                {selectFriends.map((friend, i) => (
                                  <div className={classes.friend_item}>
                                    <FriendListItem user={friend.memberName} />
                                    <button
                                      onClick={() => handleRemoveClick(friend)}
                                    >
                                      x
                                    </button>
                                  </div>
                                ))}
                              </div>
                              <span>친구초대</span>
                              <div className={classes.form_group}>
                                <input
                                  className={classes.form_style}
                                  placeholder='아이디를 입력해주세요'
                                  type='text'
                                  value={inputValue}
                                  onChange={handleInputChange}
                                  onBlur={() => setShowDropdown(false)}
                                />
                                {showDropdown && (
                                  <ul>
                                    {filteredfriends.map((friend, i) => (
                                      <li key={friend.appId}>
                                        {friend.memberName}
                                        <button
                                          onMouseDown={() =>
                                            handleFriendClick(friend)
                                          }
                                        >
                                          +
                                        </button>
                                      </li>
                                    ))}
                                  </ul>
                                )}
                              </div>
                              <button
                                className={classes.main__button}
                                type='submit'
                                onClick={createRoom}
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
