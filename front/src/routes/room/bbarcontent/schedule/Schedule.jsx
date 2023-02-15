import React, { useEffect, useMemo } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { DragDropContext } from 'react-beautiful-dnd';
import Bbar from '../../../../common/bbar/Bbar';
import './Schedule.scss';
import PlaceBox from '../place/PlaceBox';
import {
  userMarkers,
  roomDateInfo,
  scheduleInfo,
  isConfirmedChanged,
} from '../../../../app/store';
import ScheduleBox from './ScheduleBox';

function Schedule({ publishSchedule }) {
  const startEndDate = useRecoilValue(roomDateInfo); // 여행 시작,끝 날짜
  const [presentSche, setPresentSche] = useRecoilState(scheduleInfo); // 일정 정보
  const [isReset, setIsReset] = useRecoilState(isConfirmedChanged); // 일정 변경 여부

  // 시작 날짜, 끝 날짜 받아서 날짜 전체 구하는 함수
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

  console.log('들어오면? isDropped', isReset);

  // 날짜 변경할 때마다 startEndDate 다시 구하기
  useMemo(() => {
    console.log('startEndDate가 업데이트됨');
    const dateResult = getDatesStartToLast(startEndDate[0], startEndDate[1]);
    console.log(dateResult);
  }, [startEndDate]);

  // -----------------일정 관련-----------------

  // 보관함, PlaceBox에 넣을 일정 데이터 만들기
  const itemsRaw = useRecoilValue(userMarkers);
  const itemsFiltered = itemsRaw.filter(item => item.isConfirmed === true);
  const dateResult = getDatesStartToLast(startEndDate[0], startEndDate[1]);

  let scheduleboxs = [];

  // 확정된 place가 1개 이상 있고, 보관함 변경해서 리셋됐을 때
  useEffect(() => {
    if (itemsFiltered.length > 0 && isReset === true) {
      console.log('🟢🟢확정 장소가 있을 때 & 리셋됨', isReset);
      scheduleboxs = [];

      // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기
      // 여행 날짜 개수만큼 items가 빈 객체 넣기
      for (let i = 0; i < dateResult.length; i += 1) {
        scheduleboxs.push({ date: dateResult[i], items: [] });
      }
      // 보관함 장소 개수만큼 첫째날에 items 채우기
      for (let j = 0; j < itemsFiltered.length; j += 1) {
        const itemOne = itemsFiltered[j];
        scheduleboxs['0'].items.push(itemOne);
      }

      // scheduleboxs 반영 및 리셋 true -> false로 해제
      setPresentSche(scheduleboxs);
      setIsReset(false);
      publishSchedule(Object.values(scheduleboxs));
    } else if (itemsFiltered.length > 0) {
      // 확정 장소 한 개 이상, 리셋이 안 일어났을 때(일정 작업 중)
      // setPresentSche(presentSche);
    }
  }, [itemsRaw]);

  useEffect(() => {
    if (itemsFiltered.length === 0 || Object.keys(presentSche).length === 0) {
      console.log('📌📌아직 아무것도 확정 안 했을 때', isReset);

      // 가변성의 여행 날짜에 따른 placebox에 넣을 마커들 이중배열로 만들기
      scheduleboxs = [];

      for (let i = 0; i < dateResult.length; i += 1) {
        scheduleboxs.push({ date: dateResult[i], items: [] });
      }
      for (let j = 0; j < itemsFiltered.length; j += 1) {
        const itemOne = itemsFiltered[j];
        scheduleboxs['0'].items.push(itemOne);
      }

      setPresentSche(scheduleboxs);
      publishSchedule(Object.values(scheduleboxs));
    }
  }, [itemsRaw]);

  // 드래그 끝나고 일정 객체 재정렬하는 함수 onDragEnd
  const onDragEnd = DropResult => {
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
      // console.log('sc', sourceColumn);
      const destColumn = presentSche[parseInt(destination.droppableId, 10)];
      // console.log('dc', destColumn);
      const sourceItems = [...sourceColumn.items];
      const destItems = [...destColumn.items];

      // 바꿔질 애 가져오기
      const [removed] = sourceItems.splice(source.index, 1);
      destItems.splice(destination.index, 0, removed);
      const tmp = {
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
      console.log('tmp 확인', tmp);
      setPresentSche(tmp);
      publishSchedule(Object.values(tmp));
    } else {
      // 같은 날짜 안에서의 순서 변경
      const column = presentSche[parseInt(source.droppableId, 10)];
      const copiedItems = [...column.items];
      const [removed] = copiedItems.splice(source.index, 1);
      copiedItems.splice(destination.index, 0, removed);
      // 변경 내용을 반영
      const tmp = {
        ...presentSche,
        [source.droppableId]: {
          ...column,
          items: copiedItems,
        },
      };
      console.log('tmp 확인', tmp);
      setPresentSche(tmp);
      publishSchedule(Object.values(tmp));
    }
    console.log('📍 onDropEnd 끝남');
  };

  // useEffect(() => {
  //   publishSchedule(Object.values(presentSche));
  // }, [presentSche]);

  return (
    <Bbar>
      <div className='schedule_title'>
        <div className='title_title'>일정</div>
        <p>
          원하는 날짜에 장소를 드래그하여 일정을 조정할 수 있습니다. 날짜 별
          장소는 같은 색으로 지도에 표기됩니다.<div className=''></div>
        </p>
      </div>
      <div className='schedule_storage'>
        <PlaceBox boxTitle='보관함' items={itemsFiltered} />
      </div>
      <br />
      <hr />
      <DragDropContext onDragEnd={onDragEnd}>
        <div className='schedulebox_section_list'>
          <div className='schedulebox_section'>
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
        </div>
      </DragDropContext>
    </Bbar>
  );
}

export default Schedule;
