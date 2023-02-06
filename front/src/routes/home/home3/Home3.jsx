import React from 'react';
import homeGIF from '../../../app/assets/images/naver_login.png';
import classes from './Home3.module.scss';

function Home3() {
  return (
    <section className={classes.home3}>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>일정 공유 기능</p>
          <div className={classes.content_wrap}>
            <p className={classes.content}>
              친구들과 함께 여행일정을 만들어요.
            </p>
            <p className={classes.content}>
              이미지 파일로 카카오톡에 공유하여 여행 시 활용해보아요!
            </p>
          </div>
        </div>
        <button>바로 시작하기</button>
      </div>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
    </section>
  );
}

export default Home3;
