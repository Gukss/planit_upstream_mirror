import React from 'react';
import homeGIF from '../../../app/assets/images/home3.gif';
import classes from './Home3.module.scss';

function Home3() {
  return (
    <section className={classes.home3}>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>
            모두의 의견을 정리해 줄 실시간 채팅과 투표
          </p>
          <div className={classes.content_wrap}>
            <p className={classes.content}>
              여행 계획을 세울 때 의견이 충돌한다면?
            </p>
            <p className={classes.content}>
              실시간 채팅과 투표로 모두의 의견에 귀 기울여보세요.
            </p>
          </div>
        </div>
        {/* <button>바로 시작하기</button> */}
      </div>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
    </section>
  );
}

export default Home3;
