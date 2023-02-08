import React, { useCallback, useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { DragDropContext } from 'react-beautiful-dnd';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Schedule.module.scss';
import PlaceBox from '../place/PlaceBox';
import { userMarkers, roomDateInfo } from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule() {
  const itemsRaw = useRecoilValue(userMarkers);
  const itemsFiltered = itemsRaw.filter(item => item.isConfirmed === true);
  const [items, setItems] = useState(itemsFiltered);
  const items1 = itemsRaw.filter(item => item.isConfirmed === true);
  const items2 = [
    {
      id: 6,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소3',
      x: 126.44,
      y: 32.44,
    },

    {
      id: 7,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소4',
      x: 126.44,
      y: 32.44,
    },
  ];
  const items3 = [
    {
      id: 8,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소5',
      x: 126.44,
      y: 32.44,
    },

    {
      id: 9,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소6',
      x: 126.44,
      y: 32.44,
    },
    {
      id: 10,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소7',
      x: 126.44,
      y: 32.44,
    },
    {
      id: 11,
      category: 'AD5',
      userColor: '#EB5252',
      dayColor: '',
      isConfirmed: true,
      title: '숙소8',
      x: 126.44,
      y: 32.44,
    },
  ];

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

  useEffect(() => {
    console.log('startEndDate가 업데이트됨');
    const dateResult = getDatesStartToLast(startEndDate[0], startEndDate[1]);
    console.log(dateResult);
  }, [startEndDate]);

  const onDragEnd = useCallback(DropResult => {
    // 우리가 현재 가진 columns들
    const [columns, setColumns] = useState();
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

    // 재정렬 작업

    // const column = data.columns[source.droppableId];
    // console.log('column', column);
    // const newTaskIds = Array.from(column.taskIds);
    // console.log('newTaskIds', newTaskIds);
    // // 출발점에서 위치했던 원래의 원소를 제거하고 destinzation.index에 원소를 넣어서 재배열
    // newTaskIds.splice(source.index, 1);
    // newTaskIds.splice(destination.index, 0, draggableId);
    // // taskIds 변경됐으니까 column에 해당 사항 반영, 변경된 column도 data state에 반영
    // const newColumn = {
    //   ...column,
    //   taskIds: newTaskIds,
    // };

    // const newData = {
    //   ...data,
    //   columns: {
    //     ...data.columns,
    //     [newColumn.id]: newColumn,
    //   },
    // };
  }, []);

  return (
    <Bbar>
      <div className={classes.schedule_title}>
        <div className={classes.title_title}>일정</div>
        <p>
          원하는 날짜에 장소를 드래그하여 일정을 조정할 수 있습니다. 날짜 별
          장소는 같은 색으로 지도에 표기됩니다<div className=''></div>
        </p>
      </div>
      <PlaceBox boxTitle='보관함' items={items} />
      <br />
      <hr />
      <DragDropContext onDragEnd={onDragEnd}>
        <div className={classes.schedulebox_section}>
          <br />
          <ScheduleBox
            boxDate='2023.날짜데이터넣어야'
            items={items1}
            order='1'
          />
          <ScheduleBox boxDate='2023.01.12' items={items2} order='2' />
          <ScheduleBox boxDate='2023.01.13' items={items3} order='3' />
          {/* 근데 ScheduleBox는 정해진 개수가 아니라서 반복문으로 해야 한다 */}
        </div>
      </DragDropContext>
    </Bbar>
  );
}

export default Schedule;
