import React from 'react';
import './Bbar.scss';

function Bbar(props) {
  return (
    <div className='bbar'>
      <div className='bbar__header'>
        <div className='bbar__header__title'>제목</div>
        <div className='bbar__header__date'>일정 : 2023.01.14</div>
      </div>

      <hr />

      <div className='bbar__menu'>{props.children}</div>
    </div>
  );
}

export default Bbar;
