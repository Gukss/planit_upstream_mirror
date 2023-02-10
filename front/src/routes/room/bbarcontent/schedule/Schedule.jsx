import React, { useCallback, useEffect, useState } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { DragDropContext } from 'react-beautiful-dnd';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Schedule.module.scss';
import PlaceBox from '../place/PlaceBox';
import {
  userMarkers,
  roomDateInfo,
  scheduleArray,
} from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule() {
  // 시작 날짜, 끝 날짜 받아서 중간 날짜들 구하는 함수
  function getDatesStartToLast(startDate, lastDate) {
    const regex = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
    if (!(regex.test(startDate) && regex.test(lastDate)))
      return 'Not Date Format';
    const result = [];
    const curDate = new Date(startDate);
    while (curDate <= new Date(lastDate)) {
      result.push(curDate.toISOString().split('T')[0]);
      curDate.setDate(curDate.getDate() + 1);
    }
    return result;
  }
  const startEndDate = useRecoilValue(roomDateInfo);

  // 날짜 바꿀 때마다 startEndDate 다시 구하기
  useEffect(() => {
    console.log('startEndDate가 업데이트됨');
    const dateResult = getDatesStartToLast(startEndDate[0], startEndDate[1]);
    console.log(dateResult);
  }, [startEndDate]);

  // 보관함에 넣을 일정 데이터 만들기
  const itemsRaw = useRecoilValue(userMarkers);
  const itemsFiltered = itemsRaw.filter(item => item.isConfirmed === true);

  const dateResult = getDatesStartToLast(startEndDate[0], startEndDate[1]);
  const [itemsSchedule, setItemsSchedule] = useState([]);
  // const [prevSchedule, setPrevSchedule] = useRecoilState(scheduleArray);

  let scheduleboxs = [];

  useEffect(() => {
    if (itemsSchedule.length === 0 && itemsFiltered.length > 0) {
      scheduleboxs = [];
      // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기
      console.log('언제 마운트', itemsFiltered);

      // let j = 0;
      for (let i = 0; i < dateResult.length; i += 1) {
        if (i === 0) {
          scheduleboxs.push(itemsFiltered);
        } else {
          scheduleboxs.push([]);
        }
      }

      setItemsSchedule(scheduleboxs);
    }
  }, [itemsRaw]);

  // useEffect(() => {
  //   console.log('check', itemsSchedule.length);

  //   if (itemsSchedule.length > 0) {
  //     // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기
  //     console.log('언제 마운트', itemsFiltered);
  //     scheduleboxs = itemsSchedule;

  //     // for (let i = 0; i < itemsSchedule.length; i += 1) {
  //     //   for (let j =0; j < itemsSchedule[i].length; j += 1) {
  //     //     scheduleboxs[i][j] =
  //     //   }
  //     //   if (i === 0) {
  //     //     scheduleboxs.push(itemsFiltered);
  //     //   } else {
  //     //     scheduleboxs.push([]);
  //     //   }
  //     // }

  //     setItemsSchedule(scheduleboxs);
  //   }
  // }, [itemsRaw]);

  // 드래그 끝나고 배열 재정렬하는 함수 onDragEnd
  const onDragEnd = DropResult => {
    // // 우리가 현재 가진 columns들
    // const [columns, setColumns] = useState();

    const { destination, source, draggableId } = DropResult;
    if (!destination) {
      console.log('잘못된 영역에 드랍했다');
      return;
    }
    if (
      destination.droppableId === source.droppableId &&
      source.index === destination.index
    )
      return;
    console.log('onDragEnd 발생');
    console.log(destination);
    console.log(source);
    console.log(draggableId);

    if (source.droppableId !== destination.droppableId) {
      console.log('다른 날짜 간의 인덱스 변경');
      const sourceItems = itemsSchedule[parseInt(source.droppableId, 10) - 1];
      const destItems =
        itemsSchedule[parseInt(destination.droppableId, 10) - 1];
      console.log('si', sourceItems);
      console.log('di', destItems);
      // 바꾸기 전 itemsSchedule
      console.log('바꾸긴 전', itemsSchedule);
      // 바꿔질 애 가져오자
      const [removed] = sourceItems.splice(source.index, 1);
      destItems.splice(destination.index, 0, removed);
      // destItems 확인
      console.log('destItems', destItems);
      // 변경 내용을 반영
      console.log('현재', itemsSchedule);
      setItemsSchedule([...itemsSchedule]);
      console.log('최종반영', itemsSchedule);
    } else {
      // 같은 날짜 안에서의 순서 변경
      console.log('같은 날짜 안에서 index 변경');
      const dayList = itemsSchedule;
      const copiedItems = itemsSchedule[parseInt(source.droppableId, 10) - 1];
      const [removed] = copiedItems.splice(source.index, 1);
      copiedItems.splice(destination.index, 0, removed);
      dayList[parseInt(source.droppableId, 10) - 1] = copiedItems;
      setItemsSchedule(dayList);
      console.log('최종', itemsSchedule);
      // 변경 내용을 반영
    }
  };

  return (
    <Bbar>
      <div className={classes.schedule_title}>
        <div className={classes.title_title}>일정</div>
        <p>
          원하는 날짜에 장소를 드래그하여 일정을 조정할 수 있습니다. 날짜 별
          장소는 같은 색으로 지도에 표기됩니다.<div className=''></div>
        </p>
      </div>
      <PlaceBox boxTitle='보관함' items={itemsFiltered} />
      <br />
      <hr />
      <DragDropContext onDragEnd={onDragEnd}>
        <div className={classes.schedulebox_section}>
          <br />
          {itemsSchedule.map((item, index) => {
            return (
              <ScheduleBox
                items={item}
                boxDate={dateResult[index]}
                order={String(index + 1)}
              />
            );
          })}
        </div>
      </DragDropContext>
    </Bbar>
  );
}

export default Schedule;
