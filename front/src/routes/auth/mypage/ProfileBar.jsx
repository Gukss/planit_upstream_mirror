import classes from './ProfileBar.module.scss';

function ProfileBar(props) {
  return (
    <div className={classes.profilebar}>
      <section className={classes.profile}>
        <i className='bx bxs-user-circle' />
        <p className={classes.nickname}>{props.userInfo.memberAppName}</p>
        <p className={classes.username}>{props.userInfo.memberAppId}</p>
      </section>
      <section className={classes.manage}>
        <div className={classes.manageitem}>
          <i className='bx bx-rocket' />
          <p>여행 일정 관리하기</p>
        </div>
        <div className={classes.manageitem}>
          <i className='bx bx-edit-alt' />
          <p>회원 정보 수정하기</p>
        </div>
      </section>
    </div>
  );
}

export default ProfileBar;
