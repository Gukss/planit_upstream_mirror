import React from 'react';
import { Link } from 'react-router-dom';
import './AbarItem.module.scss';

const abarNavItems = [
  {
    title: '검색',
    icon: <i className='bx bx-search' />,
    to: '/',
    section: '',
    id: 1,
  },
  {
    title: '보관함',
    icon: <i className='bx bx-star'></i>,
    to: '/started',
    section: 'started',
    id: 2,
  },
  {
    title: '일정',
    icon: <i className='bx bx-calendar'></i>,
    to: '/calendar',
    section: 'calendar',
    id: 3,
  },
  {
    title: '투표',
    icon: <i className='bx bx-user'></i>,
    to: '/user',
    section: 'user',
    id: 4,
  },
  {
    title: '랜덤뽑기',
    icon: <i className='bx bx-receipt'></i>,
    to: '/order',
    section: 'order',
    id: 5,
  },
];

function Abar() {
  return (
    <div className='abar'>
      <div className='abar__logo'>PLAN!T</div>
      <div className='abar__menu'>
        {abarNavItems.map(item => (
          <Link to={item.to} key={item.id}>
            <div className='abar__menu__item'>
              <div className='abar__menu__item__icon'>{item.icon}</div>
              <div className='abar__menu__item__text'>{item.title}</div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
}

export default Abar;
