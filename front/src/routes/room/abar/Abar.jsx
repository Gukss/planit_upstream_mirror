import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import classes from './Abar.module.scss';

const abarNavItems = [
  {
    title: '검색',
    icon: <i className='bx bx-search' />,
    to: 'search',
    section: 'search',
    id: 1,
  },
  {
    title: '장소',
    icon: <i className='bx bx-star'></i>,
    to: 'place',
    section: 'place',
    id: 2,
  },
  {
    title: '일정',
    icon: <i className='bx bx-calendar'></i>,
    to: 'schedule',
    section: 'schedule',
    id: 3,
  },
  {
    title: '투표',
    icon: <i className='bx bx-box'></i>,
    to: 'vote',
    section: 'vote',
    id: 4,
  },
  {
    title: '채팅',
    icon: <i className='bx bx-message'></i>,
    to: 'chat',
    section: 'chat',
    id: 5,
  },
];

function Abar() {
  return (
    <div className={classes.abar}>
      <div className={classes.abar__logo}>PLAN!T</div>
      <div className={classes.abar__menu}>
        {abarNavItems.map(item => (
          <Link to={item.to} key={item.id}>
            <div className={classes.abar__menu__item}>
              <div className={classes.abar__menu__item__icon}>{item.icon}</div>
              <div className={classes.abar__menu__item__text}>{item.title}</div>
            </div>
          </Link>
        ))}
      </div>
      <Outlet />
    </div>
  );
}

export default Abar;
