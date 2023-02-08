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

export const currentMarker = atom({
  key: 'currentMarker',
  default: {},
});

// export const userMarkers = atom({
//   key: 'userMarkers',
//   default: [],
// });

// 커스텀할 유저마커
export const userMarkers = atom({
  key: 'userMarkers',
  default: [
    {
      id: 1,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소1',
      x: 126.44,
      y: 32.44,
    },

    {
      id: 2,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소2',
      x: 126.44,
      y: 32.44,
    },

    {
      id: 3,
      category: 'FD6',
      userColor: '#90CE0A',
      dayColor: '',
      isConfirmed: false,
      title: '식당1',
      x: 126.44,
      y: 32.44,
    },
    {
      id: 4,
      category: 'AT4',
      userColor: '#7997FE',
      dayColor: '',
      isConfirmed: false,
      title: '관광명소 성심당',
      x: 126.44,
      y: 32.44,
    },
    {
      id: 5,
      category: 'CE7',
      userColor: '#FAE635',
      dayColor: '',
      isConfirmed: true,
      title: '카페 아자카파',
      x: 126.44,
      y: 32.44,
    },
  ],
});

export const chatMessages = atom({
  key: 'chatMessages',
  default: [],
});

export const roomDateInfo = atom({
  key: 'roomDateInfo',
  default: ['2023-02-07', '2023-02-09'],
});
