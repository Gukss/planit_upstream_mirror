import React, { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import Header from '../../common/header/Header';
import Home1 from './home1/Home1';
import Home2 from './home2/Home2';
import Home3 from './home3/Home3';
import Home4 from './home4/Home4';
import { eventSource, loadingState } from '../../app/store';

import Loading from '../../common/loading/Loading';

function HomeLayout() {
  const eventSourceValue = useRecoilValue(eventSource);
  const loadingRecoil = useRecoilValue(loadingState);

  useEffect(() => {}, [eventSourceValue]);

  return (
    <div>
      {loadingRecoil ? <Loading /> : null}
      <Header />
      <Home1 />
      <Home2 />
      <Home3 />
      <Home4 />
    </div>
  );
}

export default HomeLayout;
