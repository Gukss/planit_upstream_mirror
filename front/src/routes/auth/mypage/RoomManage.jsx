import classes from './RoomManage.module.scss';
import RoomManageItem from './RoomManageItem';

function RoomManage() {
  return (
    <div className={classes.room_manage}>
      <section className={classes.schedule_ing}>
        <p className={classes.title}>진행 중인 여행 일정</p>
        <div className={classes.schedulebox}>
          <RoomManageItem />
          <RoomManageItem />
          <RoomManageItem />
          <RoomManageItem />
          <RoomManageItem />
          <RoomManageItem />
          <RoomManageItem />
        </div>
      </section>
      <section className={classes.schedule_done}>
        <p className={classes.title}>완료된 여행 일정</p>
        <div className={classes.schedulebox}>
          <p>스케줄 아이템들이 들어갈 공간입니당.</p>
        </div>
      </section>
    </div>
  );
}

export default RoomManage;
