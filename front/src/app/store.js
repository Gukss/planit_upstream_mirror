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
