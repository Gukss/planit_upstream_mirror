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
          <div className={classes.item_tag}>
            {/* {props.item.category === 'AD5' ? (
              <i className='bx bx-home' />
            ) : props.item.category === 'FD6' ? (
              <i className='bx bx-restaurant' />
            ) : (
              <p>반갑</p>
            )} */}

            {(props.item.categoryCode === 'AD5' && (
              <i className='bx bx-home' />
            )) ||
              (props.item.categoryCode === 'FD6' && (
                <i className='bx bx-restaurant' />
              )) ||
              (props.item.categoryCode === 'CE7' && (
                <i className='bx bx-coffee' />
              )) ||
              (props.item.categoryCode === 'AT4' && (
                <i className='bx bxs-castle' />
              )) ||
              (props.item.categoryCode === 'AD5' && (
                <i className='bx bx-home' />
              )) || <i className='bx bx-map' />}
          </div>

          <div className={classes.item_title}>
            <h3>{props.item.title}</h3>
            {/* <p>인덱스 {props.item.category}</p> */}
          </div>
        </div>
      )}
    </Draggable>
  );
}

export default ScheduleBoxItem;
