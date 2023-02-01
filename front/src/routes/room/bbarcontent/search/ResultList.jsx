import { useRecoilValue } from 'recoil';
import React from 'react';
import ResultListItem from './ResultListItem';
import classes from './ResultList.module.scss';
import { searchedPlaces } from '../../../../app/store';

function ResultList() {
  const resultPlaces = useRecoilValue(searchedPlaces);

  return (
    <div className={classes.result}>
      {resultPlaces.map(place => {
        return <ResultListItem place={place} />;
      })}
    </div>
  );
}

export default ResultList;
