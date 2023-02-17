import React, { useEffect, useMemo } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { DragDropContext } from 'react-beautiful-dnd';
import Bbar from '../../../../common/bbar/Bbar';
import './Schedule.scss';
// import PlaceBox from '../place/PlaceBox';
import {
  userMarkers,
  roomInfoState,
  scheduleInfo,
  isConfirmedChanged,
} from '../../../../app/store';
import ScheduleBox from './ScheduleBox';
import ScheduleStorage from './ScheduleStorage';

function Schedule({ publishSchedule }) {
  const roomDateInfo = useRecoilValue(roomInfoState); // 여행 시작,끝 날짜
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

  // 날짜 변경할 때마다 startEndDate 다시 구하기
  useMemo(() => {
    const dateResult = getDatesStartToLast(
      roomDateInfo.startDate,
      roomDateInfo.endDate
    );
  }, [roomDateInfo]);

  // -----------------일정 관련-----------------

  // 보관함, PlaceBox에 넣을 일정 데이터 만들기
  const itemsRaw = useRecoilValue(userMarkers);
  const itemsFiltered = itemsRaw.filter(item => item.isConfirmed === true);
  const dateResult = getDatesStartToLast(
    roomDateInfo.startDate,
    roomDateInfo.endDate
  );

  let scheduleboxs = [];

  // 확정된 place가 1개 이상 있고, 보관함 변경해서 리셋됐을 때
  useEffect(() => {
    if (itemsFiltered.length > 0 && isReset === true) {
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
      return;
    }
    if (
      destination.droppableId === source.droppableId &&
      source.index === destination.index
    )
      return;

    if (source.droppableId !== destination.droppableId) {
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
      setPresentSche(tmp);
      publishSchedule(Object.values(tmp));
    }
  };

  // useEffect(() => {
  //   publishSchedule(Object.values(presentSche));
  // }, [presentSche]);

  return (
    <Bbar>
      <div className='schedule_title'>
        <div className='title_title'>일정</div>
        <p>
          원하는 날짜에 장소를 드래그 앱 드랍하여 일정을 조정할 수 있습니다.
          실시간으로 친구들과 함께 일정을 바꾸어보세요.<div className=''></div>
        </p>
      </div>
      <div className='schedule_storage'>
        <ScheduleStorage boxTitle='보관함' items={itemsFiltered} />
      </div>
      <br />

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
