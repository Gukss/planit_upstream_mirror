import React, { useState, useEffect } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import ResultList from './ResultList';

import classes from './Search.module.scss';

function Search() {
  // input에 들어가는 값 변수 value
  const [value, setValue] = useState('');

  // 제출한 검색어 담는 변수 keyword
  const [keyword, setKeyword] = useState('');

  // 검색 결과값
  const [resultPlaces, setResultPlaces] = useState([]);

  // 인풋에 담긴 값을 state에 담는 함수
  const presentValue = e => {
    e.preventDefault();
    setValue(e.target.value);
    console.log(value);
  };

  // 제출한 검색어를 state에 담는 함수
  const submitKeyword = e => {
    if (e.keyCode === 13) {
      e.preventDefault();
      setKeyword(e.target.value);
      console.log(keyword);
    }
  };

  // 검색어 입력하지 않고 엔터 쳤을 경우
  const checkKeyword = () => {
    if (keyword === '') {
      console.log('검색어를 입력해주세요.');
    }
  };
  // kakao 인식시키기
  const { kakao } = window;

  // 한 번 분위기 바뀌고
  // 검색어가 바뀔 때마다 처리해줄 것들
  useEffect(() => {
    // const placestest = new kakao.maps.services.Places();

    // const callback = function (result, status) {
    //   if (status === kakao.maps.services.Status.OK) {
    //     console.log(result);
    //   }
    // };

    // placestest.keywordSearch(keyword, callback);

    // 장소 검색 객체를 생성
    const ps = new kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    // 장소 검색이 완료됐을 때 호출되는 콜백함수
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        console.log('data');
        console.log(data);
        setResultPlaces(data);
        //   // 정상적으로 검색 완료됐으면
        //   // 검색 목록과 마커를 표출
        //   displayPlaces(data);

        //   // 페이지 번호를 표출
        //   displayPagination(pagination);
        // } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        //   console.log('검색 결과가 존재하지 않습니다.');
        //   // return;
        // } else if (status === kakao.maps.services.Status.ERROR) {
        //   console.log('검색 결과 중 오류가 발생했습니다.');
        // }
      }

      // // 검색 결과 목록과 마커를 표출하는 함수
      // function displayPlaces(places) {
      //   const listEl = document.getElementById('places-list');
      //   const resultEl = document.getElementById('search-result');
      //   const fragment = document.createDocumentFragment();
      //   const bounds = new kakao.maps.LatLngBounds();

      //   // 검색 결과 목록에 추가된 항목들을 제거
      //   removeAllChildNods(listEl);
      //   // 지도에 표시되고 있는 마커를 제거
      //   removeMarker();

      //   for ( var i=0; i<places.length; i++ ) {
      //     // 마커를 생성하고 지도에 표시
      //     let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
      //     let marker = addMarker(placePosition, i, undefined);
      //     let itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성

      //     // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      //     // LatLngBounds 객체에 좌표를 추가
      //     bounds.extend(placePosition);

      //     // 마커와 검색결과 항목에 mouseover 했을때
      //     // 해당 장소에 인포윈도우에 장소명을 표시
      //     // mouseout 했을 때는 인포윈도우를 닫기
      //     (function(marker, title) {
      //       kakao.maps.event.addListener(marker, 'mouseover', function() {
      //         displayInfowindow(marker, title);
      //       });

      //       kakao.maps.event.addListener(marker, 'mouseout', function() {
      //         infowindow.close();
      //       });

      //       itemEl.onmouseover = function () {
      //         displayInfowindow(marker, title);
      //       };

      //       itemEl.onmouseout = function () {
      //         infowindow.window.close();
      //       };
      //     })(marker, places[i].place_name);

      //     fragment.appendChild(itemEl);
      //   }

      //   // 검색결과 항목들을 검색결과 목록 Element에 추가
      //   listEl.appendChild(fragment);
      //   if (resultEl) {
      //     resultEl.scrollTop = 0;
      //   }

      //   // 검색된 장소 위치를 기준으로 지도 범위를 재설정
      //   map.setBounds(bounds);
      // }
    }

    // 키워드 검색을 요청하는 함수 searchPlaces()
    function searchPlaces() {
      // 장소검색 객체를 통해 키워드로 장소 검색을 요청
      ps.keywordSearch(keyword, placesSearchCB);
    }

    // 키워드로 장소를 검색
    searchPlaces();
  }, [keyword]);
  // onSubmit={(submitKeyword, checkKeyword)}

  return (
    <Bbar>
      {/* <form className='search-form' onSubmit={(submitKeyword, checkKeyword)}> */}
      <input
        type='text'
        placeholder='검색어를 입력하세요'
        onKeyDown={submitKeyword}
      />
      {/* </form> onChange={presentValue} */}
      <div className='search-result'>
        <div className='scroll-wrapper'>
          <ul id='places-list'></ul>
        </div>
        <div id='pagination'></div>
      </div>
      <div className={classes.search}>
        <ResultList resultPlaces={resultPlaces} />
      </div>
    </Bbar>
  );
}

export default Search;
