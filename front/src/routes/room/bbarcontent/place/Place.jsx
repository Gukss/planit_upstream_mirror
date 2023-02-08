import { useRecoilValue } from 'recoil';
import React, { useEffect, useState } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Place.module.scss';
import PlaceBox from './PlaceBox';
import { userMarkers } from '../../../../app/store';

function Place() {
  // const boxTitles = ['숙소', '식당', '관광지', '카페', '기타'];
  const items = useRecoilValue(userMarkers);

  // const [itemHotel, setItemHotel] = useState([]);
  // const [itemRestaurant, setItemRestaurant] = useState([]);
  // const [itemSpot, setItemSpot] = useState([]);
  // const [itemCafe, setItemCafe] = useState([]);
  // const [itemElse, setItemElse] = useState([]);

  console.log('여기', items);

  const itemHotels = items.filter(item => item.category === 'AD5');
  const itemRestaurants = items.filter(item => item.category === 'FD6');
  const itemSpots = items.filter(item => item.category === 'AT4');
  const itemCafes = items.filter(item => item.category === 'CE7');
  // const itemElses = items.filter(item => item.category === 'AD5');

  // useEffect(() => {
  //   items.map(item => {
  //     console.log(item);
  //     if (item.category === 'AD5') {
  //       setItemHotel([...itemHotel, item]);
  //     } else if (item.category === 'FD6') {
  //       setItemRestaurant([...itemRestaurant, item]);
  //     } else if (item.category === 'AT4') {
  //       setItemSpot([...itemSpot, item]);
  //     } else if (item.category === 'CE7') {
  //       setItemCafe([...itemCafe, item]);
  //     } else {
  //       setItemElse([...itemElse, item]);
  //     }
  //     return 0;
  //   });
  // }, [items]);

  return (
    <Bbar>
      <div className={classes.place_title}>
        <div className={classes.title_title}>장소 보관함</div>
        <p>
          가고 싶은 장소를 담고 장소를 클릭하여 일정에 들어갈 확정 장소를
          선택하세요!
        </p>
      </div>
      <div className={classes.placebox_section}>
        {/* {boxTitles.map(boxTitle => {
          return <PlaceBox boxTitle={boxTitle} />;
        })} */}
        {/* {items.map((item, index) => (
          // if (item.category === 'AD5') {
          <PlaceBox key={item} boxTitle='숙소' item={item} />
          // } else if (item.category === 'FD6') {
          //   return <PlaceBox boxTtile='음식점' item={item} />;
          // }
        ))} */}
        <PlaceBox boxTitle='숙소' items={itemHotels} />
        <PlaceBox boxTitle='음식점' items={itemRestaurants} />
        <PlaceBox boxTitle='관광지' items={itemSpots} />
        <PlaceBox boxTitle='카페' items={itemCafes} />
      </div>
    </Bbar>
  );
}

export default Place;
