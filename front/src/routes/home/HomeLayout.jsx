import React, { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import Header from '../../common/header/Header';
import Home1 from './home1/Home1';
import Home2 from './home2/Home2';
import Home3 from './home3/Home3';
import Loading from '../../common/loading/Loading';
import { eventSource, loadingState } from '../../app/store';

function HomeLayout() {
  const eventSourceValue = useRecoilValue(eventSource);
  const loadingRecoil = useRecoilValue(loadingState);

  useEffect(() => {
    console.log(eventSourceValue);
  }, [eventSourceValue]);

  return (
    <div>
      {loadingRecoil ? <Loading /> : null}
      <Header />
      <Home1 />
      <Home2 />
      <Home3 />
    </div>
  );
}

export default HomeLayout;
