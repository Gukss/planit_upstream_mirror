import React, { useState, useRef } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import classes from './ScheduleBoxItem.module.scss';

function ScheduleBoxItem(props) {
  return (
    <Draggable
      draggableId={props.item.title}
      index={props.index}
      key={props.item.id}
    >
      {provided => (
        // console.log('provided가 뭐야', provided)
        <div
          className={classes.item_body}
          ref={provided.innerRef}
          {...provided.dragHandleProps}
          {...provided.draggableProps}
        >
          <div className={classes.item_tag}></div>
          <div className={classes.item_title}>
            <h3>{props.item.title}</h3>
            <p>인덱스 {props.index}</p>
          </div>
        </div>
      )}
    </Draggable>
  );
}

export default ScheduleBoxItem;
