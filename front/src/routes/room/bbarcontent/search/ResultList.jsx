import React from 'react';
import ResultListItem from './ResultListItem';
import classes from './ResultList.module.scss';

function ResultList() {
  return (
    <div className={classes.result}>
      <ResultListItem />
    </div>
  );
}

export default ResultList;
