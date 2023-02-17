import React from 'react';
import { Link } from 'react-router-dom';
import homeGIF from '../../../app/assets/images/home1_image.png';
import classes from './Home1.module.scss';

function Home1() {
  return (
    <section className={classes.home1}>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          {/* <p className={classes.title}>여행의 시작은 계획!</p> */}
          <p className={classes.title}>계획도 여행처럼!</p>
          <p className={classes.title}>친구들과 함께 즐기는 여행 계획 공간</p>
          <h1 className={classes.title_logo}>PLAN!T</h1>
        </div>
        {/* <Link to='/createroom'>
          <button>바로 시작하기</button>
        </Link> */}
      </div>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
    </section>
  );
}

export default Home1;
