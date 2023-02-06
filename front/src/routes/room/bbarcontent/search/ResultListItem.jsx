import React, { useEffect } from 'react';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { searchedPlace, userMarkers } from '../../../../app/store';
import classes from './ResultListItem.module.scss';

const items = [1, 2, 3];

function ResultListItem(props) {
  const fullCategory = props.place.category_name;
  const fullCategoryList = fullCategory.split('>');
  console.log('ResultItem 생성마다 호출');
  const categoryKeyword = fullCategoryList[fullCategoryList.length - 1];
  // console.log(categoryKeyword);
  const detailUrl = props.place.place_url;
  // 클릭시 좌표값 저장;
  const [tmp, setTmp] = useRecoilState(searchedPlace);

  const [markers, setMarkers] = useRecoilState(userMarkers);

  const onClickHandler = e => {
    setTmp({
      x: props.place.x,
      y: props.place.y,
    });
  };

  const onAddPlace = e => {
    console.log('장소 추가 버튼 누름');
    // 장소 추가하면 해줄 작업
    // 전역 state인 searchedPlaces를 업데이트할 함수 setSearchedPlaces
    // const [markers, setMarkers] = useRecoilState(userMarkers);
    // 전역 state에서 가져온 markers에 추가하려는 장소가 이미 있다면?

    console.log(markers);
    console.log(props.place);
    const newMarker = {
      category: props.place.category_group_code,
      userColor: 'red', // 동적으로 전달해야 함
      dayColor: '',
      isConfirmed: 'false',
      title: props.place.place_name,
      x: props.place.x,
      y: props.place.y,
    };
    console.log(newMarker);

    setMarkers([...markers, newMarker]);
    console.log('markers에 추가됐는지 확인', markers);
  };

  return (
    <div
      className={classes.resultitem}
      onClick={onClickHandler}
      onKeyDown={onClickHandler}
      role='button'
      tabIndex={0}
    >
      {/* <div className={classes.resultitem}> */}
      <div className={classes.resultitem__title}>
        <div className={classes.title_namecategory}>
          <div className={classes.title_name}>{props.place.place_name}</div>
          <div className={classes.title_category}>{categoryKeyword}</div>
        </div>
        <div
          className={classes.title_plus}
          onClick={onAddPlace}
          onKeyDown={onAddPlace}
          role='button'
          tabIndex={0}
        >
          <i className='bx bx-plus'></i>
        </div>
      </div>
      <div className={classes.resultitem__content}>
        <div>{props.place.road_address_name}</div>
        <div className={classes.content_easyaddress}>
          (지번) {props.place.address_name}
        </div>
        {props.place.phone && (
          <div className={classes.content_phone}>
            <i className='bx bxs-phone'></i>
            <p>{props.place.phone}</p>
          </div>
        )}
        <div>
          <a href={detailUrl} target='_blank' rel='noopener noreferrer'>
            가게 정보 확인하기
          </a>
        </div>
      </div>
      {/* <div className={classes.resultitem__photo}>
        {items.map(item => (
          <div className={classes.resultitem__photo__item} />
        ))}
      </div> */}
    </div>
  );
}

export default ResultListItem;
