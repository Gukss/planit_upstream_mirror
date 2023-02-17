import { useRecoilValue } from 'recoil';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { loadingState, userInfoState } from '../../../app/store';
import Header from '../../../common/header/Header';
import classes from './MyPage.module.scss';
import ProfileBar from './ProfileBar';
import RoomMange from './RoomManage';
import Loading from '../../../common/loading/Loading';

function MyPage() {
  const loadingRecoil = useRecoilValue(loadingState);
  const userInfo = useRecoilValue(userInfoState);
  const [myRoomInfo, setMyRoomInfo] = useState([]);
  const [tmpRoom, setTmpRoom] = useState({});
  const [roomInfo, setRoomInfo] = useState([]);

  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const leftPad = value => {
    if (value >= 10) {
      return value;
    }

    return `0${value}`;
  };

  const toStringByFormatting = (source, delimiter = '-') => {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
  };

  const reqRoomData = async e => {
    try {
      const resMemRoomData = await instance.get('/rooms/users');
      const resRoomData = await resMemRoomData.data.map((roomInfo, i) =>
        instance
          .get(`/rooms/${roomInfo.roomId}`)
          .then(res => setMyRoomInfo(myRoomInfo => [...myRoomInfo, res]))
      );
      setRoomInfo(resMemRoomData);
      // const today = toStringByFormatting(new Date(), '-');
      // const resMemRoomDataBefore = resMemRoomData.filter(
      //   resMemRoomData.startDate < today
      // );
      // const resMemRoomDataAfter = resMemRoomData.filteer(
      //   resMemRoomData.startDate >= today
      // );
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    reqRoomData();
  }, []);

  return (
    <div className={classes.mypage}>
      <Header />
      <div>빈공간</div>
      <section className={classes.content_section}>
        <ProfileBar userInfo={userInfo} />
        <RoomMange userInfo={myRoomInfo} roomInfo={roomInfo} />
      </section>
      {loadingRecoil ? <Loading /> : null}
    </div>
  );
}

export default MyPage;
