import React, { useCallback, useEffect, useState } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { DragDropContext } from 'react-beautiful-dnd';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Schedule.module.scss';
import PlaceBox from '../place/PlaceBox';
import {
  userMarkers,
  roomDateInfo,
  scheduleInfo,
  isScheduleChanged,
} from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule() {
  const [presentSche, setPresentSche] = useRecoilState(scheduleInfo);
  const [isDropped, setIsDropped] = useRecoilState(isScheduleChanged);

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
  // const [itemsSchedule, setItemsSchedule] = useState([]);

  // const [seq2, setSeq2] = useState(false);
  let scheduleboxs = {};

  // 일정으로 옮긴 건 아무것도 없고, 확정된 place가 1개 이상 있을 때
  useEffect(() => {
    setIsDropped(false);
    console.log('isD false로 바꿔주는 곳 들어옴', isDropped);
  }, [itemsRaw]);

  useEffect(() => {
    if (itemsFiltered.length > 0 && isDropped === false) {
      console.log('🟢🟢확정 장소가 있을 때', isDropped);
      console.log(presentSche);
      scheduleboxs = [];
      // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기

      // let j = 0;
      for (let i = 0; i < dateResult.length; i += 1) {
        // scheduleboxs.push({ `${i}`: { date: dateResult[i], items: [] } });
        scheduleboxs.push({ date: dateResult[i], items: [] });
        // if (i === 0) {
        //   scheduleboxs.push(itemsFiltered);
        // } else {
        //   scheduleboxs.push([]);
        // }
      }
      for (let j = 0; j < itemsFiltered.length; j += 1) {
        const itemOne = itemsFiltered[j];
        scheduleboxs['0'].items.push(itemOne);
      }

      setPresentSche(scheduleboxs);
      console.log(scheduleboxs);
    }
  }, [itemsRaw]);

  useEffect(() => {
    if (presentSche.length === undefined && isDropped === false) {
      console.log('📌📌📌아직 아무것도 확정 안 했을 때', isDropped);
      console.log('현재 일정 길이는?', presentSche.length);
      console.log('ps', presentSche);
      scheduleboxs = [];
      // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기

      // let j = 0;
      for (let i = 0; i < dateResult.length; i += 1) {
        scheduleboxs.push({ date: dateResult[i], items: [] });
      }
      for (let j = 0; j < itemsFiltered.length; j += 1) {
        const itemOne = itemsFiltered[j];
        scheduleboxs['0'].items.push(itemOne);
      }

      setPresentSche(scheduleboxs);
      console.log(scheduleboxs);
      console.log('여기', Object.values(presentSche));
    }
  }, [itemsRaw]);

  // useEffect(() => {
  //   // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기
  //   console.log('언제 마운트');

  //   // let j = 0;
  //   for (let i = 0; i < dateResult.length; i += 1) {
  //     if (i === 0) {
  //       const itemOne = itemsFiltered;
  //       setItemsSchedule(prev => [...itemsSchedule, itemOne]);
  //       // itemsSchedule.push(itemOne);
  //     } else {
  //       const itemOne = [];
  //       setItemsSchedule(prev => [...itemsSchedule, itemOne]);
  //       // itemsSchedule.push(itemOne);
  //     }
  //     // const itemOne = [
  //     //   itemsFiltered.slice(
  //     //     i,
  //     //     i + Math.floor(itemsFiltered.length / dateResult.length)
  //     //   ),
  //     // ];

  //     // let j += Math.floor(itemsFiltered.length / dateResult.length);
  //   }
  //   // setTimeout(() => console.log('이중배열이어야', itemsSchedule), 5000);
  //   console.log(itemsFiltered.length, dateResult.length);
  //   console.log('이중배열', itemsSchedule);
  // }, [itemsFiltered]);

  // 드래그 끝나고 배열 재정렬하는 함수 onDragEnd
  const onDragEnd = DropResult => {
    // // 우리가 현재 가진 columns들

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

    if (source.droppableId !== destination.droppableId) {
      console.log('다른 날짜 간의 인덱스 변경');
      const sourceColumn = presentSche[parseInt(source.droppableId, 10)];
      console.log('sc', sourceColumn);
      const destColumn = presentSche[parseInt(destination.droppableId, 10)];
      console.log('dc', destColumn);
      const sourceItems = [...sourceColumn.items];
      const destItems = [...destColumn.items];

      // 바꾸기 전 itemsSchedule
      console.log('바꾸긴 전', presentSche);
      // 바꿔질 애 가져오자
      const [removed] = sourceItems.splice(source.index, 1);
      destItems.splice(destination.index, 0, removed);
      console.log('si', sourceItems);
      console.log('di', destItems);
      // console.log('copiedSourceItems', copiedSourceItems);
      // console.log('copiedDestItems', copiedDestItems);
      console.log('columns', presentSche);
      console.log('sourceColumn', sourceColumn, sourceItems);
      console.log('destColumn', destColumn, destItems);
      const test = {
        ...presentSche,
        [source.droppableId]: {
          ...sourceColumn,
          items: sourceItems,
        },
        [destination.droppableId]: {
          ...destColumn,
          items: destItems,
        },
      };
      console.log('테스트', test);
      setPresentSche(test);
      // console.log('최종반영', [
      //   [...presentSche],
      //   [...copiedDestItems],
      //   [...copiedSourceItems],
      // ]);
    } else {
      // 같은 날짜 안에서의 순서 변경
      const column = presentSche[parseInt(source.droppableId, 10)];
      const copiedItems = [...column.items];
      const [removed] = copiedItems.splice(source.index, 1);
      copiedItems.splice(destination.index, 0, removed);
      // 변경 내용을 반영
      const test = {
        ...presentSche,
        [source.droppableId]: {
          ...column,
          items: copiedItems,
        },
      };
      console.log('테스트', test);
      setPresentSche(test);
    }
    setIsDropped(true);
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
          {Object.values(presentSche).map((item, index) => {
            return (
              <ScheduleBox
                items={item.items}
                boxDate={item.date}
                order={String(index)}
              />
            );
          })}
        </div>
      </DragDropContext>
    </Bbar>
  );
}

export default Schedule;
