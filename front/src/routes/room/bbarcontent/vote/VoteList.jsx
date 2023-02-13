import React from 'react';
import axios from 'axios';
import classes from './VoteList.module.scss';
import VoteListItem from './VoteListItem';
import logo2 from '../../../../app/assets/images/logo2.png';

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
  return (
    <div className={classes.result_first}>
      <img src={logo2} alt='logo1' />
      <p>+ 버튼으로 투표 생성!</p>
    </div>
  );
}

export default VoteList;
