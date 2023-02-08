import { useRecoilValue } from 'recoil';
import React from 'react';
import ResultListItem from './ResultListItem';
import classes from './ResultList.module.scss';
import { searchedPlaces } from '../../../../app/store';
import logo1 from '../../../../app/assets/images/logo1.png';

function ResultList() {
  const resultPlaces = useRecoilValue(searchedPlaces);

  if (resultPlaces.length >= 1) {
    return (
      <div className={classes.result}>
        {resultPlaces.map((place, index) => {
          return <ResultListItem place={place} index={index} />;
        })}
      </div>
    );
  }
  return (
    <div className={classes.result_first}>
      <img src={logo1} alt='logo1' />
      <p>지금 당장 떠나고 싶은 곳은?</p>
    </div>
  );
}

export default ResultList;
