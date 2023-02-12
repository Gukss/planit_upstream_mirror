import React from 'react';
import axios from 'axios';
import classes from './VoteList.module.scss';
import VoteListItem from './VoteListItem';

function VoteList(props) {
  console.log('votelist');
  const votes = props.voteInfo;
  console.log(votes);
  // testApiCall();

  if (votes.length > 0) {
    return (
      <div className={classes.vote_list_section}>
        {votes.map(vote => {
          return <VoteListItem vote={vote} key={Math.random()} />;
        })}
      </div>
    );
  }
}

export default VoteList;
