import React, { useState } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import classes from './ScheduleBox.module.scss';
import ScheduleBoxItem from './ScheduleBoxItem';

function ScheduleBox(props) {
  console.log('ScheduleBox에 초기 props', props);
  return (
    <div>
      {/* <DragDropContext> */}
      <Droppable droppableId={props.order}>
        {provided => (
          <div
            className={classes.box_content}
            {...provided.droppableProps}
            ref={provided.innerRef}
          >
            <p>{props.boxDate}</p>
            {props.items.length ? (
              props.items.map((item, index) => {
                return <ScheduleBoxItem item={item} index={index} />;
              })
            ) : (
              <div className={classes.box_content_empty}>
                <p>등록한 일정이 없습니다</p>
              </div>
            )}
            {provided.placeholder}
          </div>
        )}
      </Droppable>
      {/* </DragDropContext> */}
    </div>
  );
}

export default ScheduleBox;
