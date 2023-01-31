import React from 'react';
import ResultListItem from './ResultListItem';
import classes from './ResultList.module.scss';

function ResultList(props) {
  return (
    <div className={classes.result}>
      {/* {props.resultPlaces.map(place => (
        <div key={place.address_name}>
          <p>{place.place_name}</p>
          <p>{place.x}</p>
          <p>{place.y}</p>
          <hr />
        </div>
      ))} */}
      {props.resultPlaces.map(place => {
        return <ResultListItem place={place} />;
      })}
    </div>
  );
}

export default ResultList;
