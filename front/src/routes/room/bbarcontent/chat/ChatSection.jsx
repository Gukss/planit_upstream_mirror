import { useEffect, useRef } from 'react';
import { useRecoilValue } from 'recoil';
import classes from './ChatSection.module.scss';
import {
  chatMessages,
  userInfoState,
  roomInfoState,
} from '../../../../app/store';

function ChatSection() {
  const messageList = useRecoilValue(chatMessages);
  const memberInfo = useRecoilValue(userInfoState);
  const roomInfo = useRecoilValue(roomInfoState);
  const memberColor = roomInfo.colorCode;
  // const date = new Date();
  // console.log('date', date);
  const scrollRef = useRef();

  useEffect(() => {
    console.log('메세지 리스트 바뀔 때마다 호출', scrollRef.current);
    if (scrollRef.current.lastElementChild) {
      scrollRef.current.lastElementChild.scrollIntoView({
        behavior: 'smooth',
      });
    }
  }, [messageList.length]);

  return (
    <div className={classes.chat_section} ref={scrollRef}>
      {messageList.map(messageListItem => {
        return (
          <div>
            {messageListItem.memberId !== memberInfo.memberId ? (
              <div>
                <i
                  className='bx bxs-user-circle'
                  style={{ color: memberColor }}
                ></i>
              </div>
            ) : null}
            <div
              className={
                messageListItem.memberId === memberInfo.memberId
                  ? classes.chat_message_section_me
                  : classes.chat_message_section_you
              }
            >
              <p>{messageListItem.message}</p>
            </div>
            {/* <div>{(date.getMinutes(), date.getMinutes())}</div> */}
          </div>
        );
      })}
    </div>
  );
}

export default ChatSection;
