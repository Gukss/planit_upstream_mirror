import React from 'react';
import homeGIF from '../../../app/assets/images/home2.gif';
import classes from './Home2.module.scss';

function Home2() {
  return (
    <section className={classes.home3}>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>실시간으로 공유되는 마커</p>
          <div className={classes.content_wrap}>
            <p className={classes.content}>
              원하는 장소를 자신의 색깔로 저장해보아요.
            </p>
            <p className={classes.content}>
              내가 표시한 마커를 실시간으로 다른 친구들에게 보여줄 수 있어요!
            </p>
          </div>
        </div>
        {/* <button>바로 시작하기</button> */}
      </div>
    </section>
  );
}

export default Home2;
