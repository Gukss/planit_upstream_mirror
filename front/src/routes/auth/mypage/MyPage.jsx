import { useRecoilValue } from 'recoil';
import axios from 'axios';
import { userInfoState } from '../../../app/store';
import Header from '../../../common/header/Header';
import classes from './MyPage.module.scss';
import ProfileBar from './ProfileBar';
import RoomMange from './RoomManage';

function MyPage() {
  const userInfo = useRecoilValue(userInfoState);

  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
    },
  });

  // const resRoomData = async e => {
  //   try {
  //     await instance.post('/')
  //   }
  // }

  return (
    <div className={classes.mypage}>
      <Header />
      <div>빈공간</div>
      <section className={classes.content_section}>
        <ProfileBar userInfo={userInfo} />
        <RoomMange userInfo={userInfo} />
      </section>
    </div>
  );
}

export default MyPage;