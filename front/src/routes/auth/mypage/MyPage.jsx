import Header from '../../../common/header/Header';
import classes from './MyPage.module.scss';
import ProfileBar from './ProfileBar';
import Schedule from './Schedules';

function MyPage() {
  return (
    <div className={classes.mypage}>
      <Header />
      <div>빈공간</div>
      <section className={classes.content_section}>
        <ProfileBar />
        <Schedule />
      </section>
    </div>
  );
}

export default MyPage;
