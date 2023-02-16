import React from 'react';
import homeGIF from '../../../app/assets/images/home4.gif';
import classes from './Home4.module.scss';

function Home4() {
  return (
    <section className={classes.home4}>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>언제든지 쉽게 꺼내볼 수 있는 일정</p>
          <div className={classes.content_wrap}>
            <p className={classes.content}>여행 일정이 어떻게 되었더라?</p>
            <p className={classes.content}>
              다시 사이트를 방문할 필요 없이, 일정을 이미지로 저장하여 여행 시
              언제나 손쉽게 꺼내보아요!
            </p>
          </div>
        </div>
        {/* <button>바로 시작하기</button> */}
      </div>
    </section>
  );
}

export default Home4;
