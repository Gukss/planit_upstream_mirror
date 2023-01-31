import React from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import ResultList from './ResultList';

import classes from './Search.module.scss';

function Search() {
  return (
    <Bbar>
      <input type='text' placeholder='검색어를 입력하세요' />
      <div className={classes.search}>
        <ResultList />
      </div>
    </Bbar>
  );
}

export default Search;
