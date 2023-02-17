import classes from './RoomManage.module.scss';
import RoomManageItem from './RoomManageItem';

function RoomManage(props) {
  return (
    <div className={classes.room_manage}>
      <section className={classes.schedule_ing}>
        <p className={classes.title}>진행 중인 여행 일정</p>
        <div className={classes.schedulebox}>
          {props.userInfo.map((info, i) => (
            <RoomManageItem
              roomData={info.data}
              roomInfo={props.roomInfo.data[i]}
            />
          ))}
        </div>
      </section>
      <section className={classes.schedule_done}>
        <p className={classes.title}>완료된 여행 일정</p>
        <div className={classes.schedulebox}>
          <div>완료된 일정이 없습니다</div>
        </div>
      </section>
    </div>
  );
}

export default RoomManage;
