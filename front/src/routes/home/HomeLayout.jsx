import React from 'react';
import Header from '../../common/header/Header';
import Home1 from './home1/Home1';
import Home2 from './home2/Home2';

function HomeLayout() {
  return (
    <div>
      <Header />
      <Home1 />
      <Home2 />
    </div>
  );
}

export default HomeLayout;
