import { atom } from 'recoil';

// 로그인 체크
export const isLogin = atom({
  key: 'isLogin',
  default: false,
  // default: true,
});

export const dateRangeState = atom({
  key: 'dateRangeState',
  default: { startDate: new Date(), endDate: new Date() },
});

// --------------------------- 마커 ---------------------------------

// 검색 결과 중 선택된 위치로 이동하기 위한 정보
export const searchedPlaces = atom({
  key: 'searchedPlaces',
  default: [],
});

// 지도 이동을 위한 위치 좌표.{ x: 33.450701, y: 126.570667 }
export const searchedPlace = atom({
  key: 'searchedPlace',
  default: {},
});

// 해당 좌표에 마커, 커스텀오버레이 표시 위한 정보
export const currentMarker = atom({
  key: 'currentMarker',
  default: {},
});

// 선택한 정보 마커, 커스텀오버레이 둘다 제거
export const removeInformation = atom({
  key: 'removeInformation',
  default: {},
});

// 커스텀할 유저마커 ( 배열 안에 빈 객체 추가 )
export const userMarkers = atom({
  key: 'userMarkers',
  default: [
    // {
    // id: '',
    // categoryCode: '',
    // colorCode: '#',
    // isConfirmed: false,
    // title: '',
    // categoryName: '',
    // x: '',
    // y: '',
    // },
    // {
    //   id: 'dummy_2',
    //   categoryCode: 'AD5',
    //   colorCode: '#EB5252',
    //   isConfirmed: false,
    //   title: '숙소2',
    //   x: 126.44,
    //   y: 32.44,
    // },
    // {
    //   id: 'dummy_3',
    //   categoryCode: 'FD6',
    //   colorCode: '#90CE0A',
    //   isConfirmed: false,
    //   title: '식당1',
    //   categoryName: '식당',
    //   x: 126.44,
    //   y: 32.44,
    // },
    // {
    //   id: 'dummy_4',
    //   categoryCode: 'AT4',
    //   colorCode: '#7997FE',
    //   isConfirmed: false,
    //   title: '관광명소 성심당',
    //   categoryName: '식당',
    //   x: 126.44,
    //   y: 32.44,
    // },
  ],
});

// 카테고리 마커 이미지 타입 전송
export const categoryCheck = atom({
  key: 'categoryCheck',
  default: {
    code: '',
    imageUrl: '',
  },
});

// -----------------------------------------------------------

// 마커 publish 용
export const markerFlag = atom({
  key: 'markerFlag',
  default: [],
});

// socket data 관리
export const chatMessages = atom({
  key: 'chatMessages',
  default: [],
});

// socket data Marker 관리
export const socketMarkers = atom({
  key: 'socketMarkers',
  default: [],
});

// token 값.
export const userInfoState = atom({
  key: 'userInfo',
  default: { memberId: -1, memberAppId: '', memberAppName: '', token: '' },
});

//
export const roomDateInfo = atom({
  key: 'roomDateInfo',
  default: ['2023-02-07', '2023-02-09'],
});

// -----------------------------------------------------------

// 일정 관리
export const scheduleInfo = atom({
  key: 'scheduleInfo',
  default: [],
});

// 일정 변동 여부 - 재방문했을 때 기존 일정 유지하기 위한 변수
export const isConfirmedChanged = atom({
  key: 'isConfirmedChanged',
  default: false,
});

// 투표 관리
export const voteInformation = atom({
  key: 'voteInformation',
  default: [],
});

// 투표 여부 확인(local 별 관리)
export const isVoted = atom({
  key: 'isVoted',
  default: [],
  // { voteTitle: info.value.title, memberVote: false }
});

// 투표 publish 용
export const voteFlag = atom({
  key: 'voteFlag',
  default: [],
});

// 친구 초대 알림 체크
export const eventSource = atom({
  key: 'eventSource',
  default: [],
});

// 유저 방 조회(axios)
export const checkUserRoom = atom({
  key: 'checkUserRoom',
  default: [],
});

// room ID 받았슴당
export const roomPK = atom({
  key: 'roomId',
  default: -1,
});

export const roomInfoState = atom({
  key: 'roomInfo',
  default: {
    // roomId: -1,
    // roomName: '',
    // startDate: '',
    // endDate: '',
    // colorCode: '',
  },
});

// 전역에 처리하는 용
export const stompClient = atom({
  key: 'stompClient',
  default: null,
});
