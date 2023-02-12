import React, { useState } from 'react';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
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
            {props.items.map((item, index) => {
              return <ScheduleBoxItem item={item} index={index} />;
            })}
<<<<<<< HEAD
            {/* <p>{props.items}</p> */}
=======
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
            {provided.placeholder}
          </div>
        )}
      </Droppable>
      {/* </DragDropContext> */}
    </div>
  );
}

export default ScheduleBox;
