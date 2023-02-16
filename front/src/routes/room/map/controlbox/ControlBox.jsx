import React from 'react';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { categoryCheck } from '../../../../app/store';
import classes from './ControlBox.module.scss';

function ControlBox() {
  const setCategory = useSetRecoilState(categoryCheck);

  const categoryFood = () => {
    setCategory({
      code: 'FD6',
      imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448653.png',
    });
  };

  const categoryHotel = () => {
    setCategory({
      code: 'AD5',
      imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448561.png',
    });
  };

  const categoryCafe = () => {
    setCategory({
      code: 'CE7',
      imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448415.png',
    });
  };

  const categoryTrip = () => {
    setCategory({
      code: 'AT4',
      imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448618.png',
    });
  };

  // const categorySubway = () => {
  //   setCategory(prev => {
  //     return {
  //       code: 'SW8',
  //       imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448683.png',
  //     };
  //   });
  // };

  // 방정보 가져오기
  // const

  return (
    <div className={classes.control_box}>
      <div
        className={classes.control_box_item}
        onClick={categoryHotel}
        onKeyDown={categoryHotel}
        role='button'
        tabIndex={0}
      >
        <div className={classes.title}>숙소</div>
        <img
          src='https://cdn-icons-png.flaticon.com/512/3448/3448561.png'
          alt='#'
        />
      </div>
      <hr />
      <div
        className={classes.control_box_item}
        onClick={categoryFood}
        onKeyDown={categoryFood}
        role='button'
        tabIndex={0}
      >
        <div className={classes.title}>음식점</div>
        <img
          src='https://cdn-icons-png.flaticon.com/512/3448/3448653.png'
          alt='#'
        />
      </div>
      <hr />
      <div
        className={classes.control_box_item}
        onClick={categoryCafe}
        onKeyDown={categoryCafe}
        role='button'
        tabIndex={0}
      >
        <div className={classes.title}>카페</div>
        <img
          src='https://cdn-icons-png.flaticon.com/512/3448/3448415.png'
          alt='#'
        />
      </div>
      <hr />
      <div
        className={classes.control_box_item}
        onClick={categoryTrip}
        onKeyDown={categoryTrip}
        role='button'
        tabIndex={0}
      >
        <div className={classes.title}>관광지</div>
        <img
          src='https://cdn-icons-png.flaticon.com/512/3448/3448618.png'
          alt='#'
        />
      </div>
    </div>
  );
}

export default ControlBox;
