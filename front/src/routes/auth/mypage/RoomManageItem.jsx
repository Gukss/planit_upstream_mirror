import classes from './RoomManageItem.module.scss';

function RoomManageItem() {
  return (
    <div className={classes.room_manageitem}>
      <section className={classes.title_section}>
        <p className={classes.title}>서울 여행</p>
        <p className={classes.date}>2023.01.15~2023.02.06</p>
      </section>
      <hr />
      <section className={classes.content_section}>
        <p className={classes.created_at}>생성일 : 2023.01.01</p>
        <p className={classes.participants}>
          참여자 : ALL | HI | 언도 | 언더더씨 | gdkfjdfk68 | hihihi | helllllo |
          핫팩
        </p>
      </section>
      <section className={classes.button_section}>
        <button className={classes.button_remove}>삭제</button>
        <button className={classes.button_enter}>입장</button>
      </section>
    </div>
  );
}

export default RoomManageItem;
