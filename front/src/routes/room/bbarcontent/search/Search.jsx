import { useRecoilState, useSetRecoilState } from 'recoil';
import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import Bbar from '../../../../common/bbar/Bbar';
import ResultList from './ResultList';

import classes from './Search.module.scss';
import { searchedPlaces } from '../../../../app/store';

function Search() {
  // input에 들어가는 값 변수 value
  const [value, setValue] = useState('');

  // 제출한 검색어 담는 변수 keyword
  const [keyword, setKeyword] = useState('');

  // 전역 state인 searchedPlaces를 업데이트할 함수 setSearchedPlaces
  const setSearchedPlaces = useSetRecoilState(searchedPlaces);

  // // 인풋에 담긴 값을 state에 담는 함수(form으로 할지, input으로 엔터로 할지 추후에 확정)
  // const presentValue = e => {
  //   e.preventDefault();
  //   setValue(e.target.value);
  //   console.log(value);
  // };

  // 제출한 검색어를 state에 담는 함수
  const submitKeyword = e => {
    if (e.keyCode === 13) {
      e.preventDefault();
      setKeyword(e.target.value);
      console.log(keyword);
    }
  };

  // // 검색어 입력하지 않고 엔터 쳤을 경우(추후에 마저 구현)
  // const checkKeyword = () => {
  //   if (keyword === '') {
  //     console.log('검색어를 입력해주세요.');
  //   }
  // };

  // kakao 인식시키기
  const { kakao } = window;

  // 검색어가 바뀔 때마다 처리해줄 것들
  useEffect(() => {
    // 장소 검색 객체를 생성
    const ps = new kakao.maps.services.Places();

    // 장소 검색이 완료됐을 때 호출되는 콜백함수
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색 결과를 searchedPlaces에 반영
        setSearchedPlaces(data);

        //   // 페이지 번호를 표출(추후에 추가)
        //   displayPagination(pagination);
        // } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        //   console.log('검색 결과가 존재하지 않습니다.');
        //   // return;
        // } else if (status === kakao.maps.services.Status.ERROR) {
        //   console.log('검색 결과 중 오류가 발생했습니다.');
        // }
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        Swal.fire({
          icon: 'error',
          // title: '검색 결과가 존재하지 않습니다.',
          text: '검색 결과가 존재하지 않습니다.',
          // footer: '<a href="">Why do I have this issue?</a>',
          confirmButtonColor: '#2B3F6B',
        });
        setSearchedPlaces([]);
        setValue('');
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
      }
    }

    // 키워드 검색을 요청하는 함수 searchPlaces() 선언
    function searchPlaces() {
      // 장소검색 객체를 통해 키워드로 장소 검색을 요청
      ps.keywordSearch(keyword, placesSearchCB);
    }

    // 키워드로 장소를 검색 실행
    searchPlaces();
  }, [keyword]);
  // onSubmit={(submitKeyword, checkKeyword)} (form으로 제출?m input으로 제출?)

  return (
    <Bbar>
      {/* <form className='search-form' onSubmit={(submitKeyword, checkKeyword)}> */}
      <label>
        <i className='bx bx-search' />
        <input
          type='text'
          placeholder='검색어를 입력하세요'
          onKeyDown={submitKeyword}
        />
      </label>

      {/* </form> onChange={presentValue} */}
      <div className='search-result'>
        <div className='scroll-wrapper'>
          <ul id='places-list'></ul>
        </div>
        <div id='pagination'></div>
      </div>
      <div className={classes.search}>
        <ResultList />
      </div>
    </Bbar>
  );
}

export default Search;
