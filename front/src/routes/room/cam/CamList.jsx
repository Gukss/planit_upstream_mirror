import React from 'react';
import CamListItem from './CamListItem';
import classes from './CamList.module.scss';
import CTBLayout from './camtoolbox/CTBLayout';

function CamList() {
  const camItems = [
    {
      user: 'user 1',
      id: 'cam1',
    },
    {
      user: 'user2',
      id: 'cam2',
    },
    {
      user: 'user3',
      id: 'cam3',
    },
    {
      user: 'user4',
      id: 'cam4',
    },
  ];

  return (
    <div className={classes.camlist}>
      {camItems.map(item => (
        <div className={classes.camlist__item}>
          <CamListItem user={item.user} key={item.id} />
        </div>
      ))}
      <div className={classes.ctb}>
        <CTBLayout />
      </div>
    </div>
  );
}

export default CamList;
