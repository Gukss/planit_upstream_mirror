import { useRecoilValue } from 'recoil';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { userInfoState } from '../../../app/store';
import Header from '../../../common/header/Header';
import classes from './MyPage.module.scss';
import ProfileBar from './ProfileBar';
import RoomMange from './RoomManage';

function MyPage() {
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

  const reqRoomData = async e => {
    try {
      const resMemRoomData = await instance.get('/rooms/users');
      const resRoomData = await resMemRoomData.data.map((roomInfo, i) =>
        instance
          .get(`/rooms/${roomInfo.roomId}`)
          .then(res => setMyRoomInfo(myRoomInfo => [...myRoomInfo, res]))
      );
      setRoomInfo(resMemRoomData);
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
    </div>
  );
}

export default MyPage;
