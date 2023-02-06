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

export const userMarkers = atom({
  key: 'userMarkers',
  default: [],
});

// export const userMarkers = atom({
//   key: 'userMarkers',
//   default: [
//     {
//       category: 'AD5',
//       userColor: 'grey',
//       dayColor: '',
//       isConfirmed: 'false',
//       title: '성심당',
//       x: 126.44,
//       y: 32.44,
//     },
//   ],
// });
