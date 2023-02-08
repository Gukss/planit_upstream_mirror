import { useRecoilValue } from 'recoil';
import React, { useState } from 'react';
import axios from 'axios';
import classes from './VoteList.module.scss';
import VoteListItem from './VoteListItem';

function VoteList() {
  const testApiCall = async () => {
    try {
      const response = await axios.get('http://i8b202.p.ssafy.io/api/members');
      const votesTest = response.data;

      console.log('votesTest>>', votesTest);
    } catch (err) {
      console.log('err >>', err);
    }
  };
  testApiCall();

  const [votes, setVotes] = useState([
    {
      title: '해운대 vs 광안리',
      vote_item: [
        { vote_item_name: '해운대', count: 2 },
        { vote_item_name: '광안리', count: 4 },
      ],
    },
    {
      title: '뭐 타고 다닐까 정하기',
      vote_item: [
        { vote_item_name: '택시', count: 2 },
        { vote_item_name: '버스', count: 1 },
        { vote_item_name: '쏘카 빌리기', count: 1 },
        { vote_item_name: '그냥 걷자', count: 1 },
      ],
    },
  ]);

  return (
    <div className={classes.vote_list_section}>
      {votes.map(vote => {
        return <VoteListItem vote={vote} />;
      })}
    </div>
  );
}

export default VoteList;
