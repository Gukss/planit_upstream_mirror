import React from 'react';

import classes from './SaveIcon.module.scss';

const sendAxios = () => {
  // ddd
};
// https://www.flaticon.com/kr/free-icon/save-file_423081?term=%EC%A0%80%EC%9E%A5&page=8&position=3&origin=search&related_id=423081
function SaveIcon() {
  return (
    <div
      className={classes.save}
      onClick={sendAxios}
      onKeyDown={sendAxios}
      tabIndex={0}
      role='button'
    >
      <img src='https://cdn-icons-png.flaticon.com/512/423/423081.png' alt='' />
      <img
        src='https://cdn-icons-png.flaticon.com/512/4087/4087925.png'
        alt=''
      />
    </div>
  );
}

export default SaveIcon;
