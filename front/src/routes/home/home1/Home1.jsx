import React from 'react';
import { Link } from 'react-router-dom';
import homeGIF from '../../../app/assets/images/naver_login.png';
import classes from './Home.module.scss';

function Home1() {
  return (
    <section className={classes.home1}>
      <div className={classes.text_section}>
        <div className={classes.title_wrap}>
          <p className={classes.title}>친구와 함께하는</p>
          <p className={classes.title}>행복한 여행 계획</p>
          <p className={classes.title_logo}>PLAN!T</p>
        </div>
        <Link to='/createroom'>
          <button>바로 시작하기</button>
        </Link>
      </div>
      <div className={classes.gif_section}>
        <img src={homeGIF} alt='' />
      </div>
    </section>
  );
}

export default Home1;
