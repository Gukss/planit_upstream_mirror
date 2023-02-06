import React from 'react';
import homeGIF from '../../../app/assets/images/naver_login.png';
import classes from './Home2.module.scss';

function Home2() {
  return (
    <section className={classes.home2}>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>친구와 함께해요</p>
          <p className={classes.content}>
            친구들을 초대하고 실시간 음성으로 대화를 나눠요.
          </p>
          <p className={classes.content}>채팅을 통해서도 소통할 수 있습니다.</p>
        </div>
        <button>바로 시작하기</button>
      </div>
    </section>
  );
}

export default Home2;
