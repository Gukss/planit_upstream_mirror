import { atom } from 'recoil';

export const isLogin = atom({
  key: 'isLogin',
  //   default: false,
  default: true,
});

export const dateRangeState = atom({
  key: 'dateRangeState',
  default: { startDate: new Date(), endDate: new Date() },
});
