import React, { useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { searchedPlace } from '../../../../app/store';
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

  const onClickHandler = e => {
    setTmp({
      x: props.place.x,
      y: props.place.y,
    });
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
        <i className='bx bx-plus'></i>
      </div>
      <div className={classes.resultitem__content}>
        <div>{props.place.road_address_name}</div>
        <div className={classes.content_easyaddress}>
          (지번) {props.place.address_name}
        </div>
        <div className={classes.content_phone}>
          <i className='bx bxs-phone'></i>
          <p>{props.place.phone}</p>
        </div>
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
