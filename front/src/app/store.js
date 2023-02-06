import { atom } from 'recoil';

// 로그인 체크
export const isLogin = atom({
  key: 'isLogin',
  // default: false,
  default: true,
});

export const dateRangeState = atom({
  key: 'dateRangeState',
  default: { startDate: new Date(), endDate: new Date() },
});

export const searchedPlaces = atom({
  key: 'searchedPlaces',
  default: [],
});

export const searchedPlace = atom({
  key: 'searchedPlace',
  default: {},
  // default: { x: 33.450701, y: 126.570667 },
});

// 커스텀할 유저마커
export const userMarkers = atom({
  key: 'userMarkers',
  default: [
    {
      category: 'AD5',
      userColor: 'grey',
      dayColor: '',
      isConfirmed: 'false',
      title: '숙소1',
      x: 126.44,
      y: 32.44,
    },

    {
      category: 'AD5',
      userColor: 'red',
      dayColor: '',
      isConfirmed: 'false',
      title: '숙소2',
      x: 126.44,
      y: 32.44,
    },

    {
      category: 'FD6',
      userColor: 'grey',
      dayColor: '',
      isConfirmed: 'false',
      title: '식당1',
      x: 126.44,
      y: 32.44,
    },
    {
      category: 'AT4',
      userColor: 'grey',
      dayColor: '',
      isConfirmed: 'false',
      title: '관광명소 성심당',
      x: 126.44,
      y: 32.44,
    },
    {
      category: 'CE7',
      userColor: 'grey',
      dayColor: '',
      isConfirmed: 'false',
      title: '카페 아자카파',
      x: 126.44,
      y: 32.44,
    },
  ],
});
