import React, { useRef } from 'react';
import Bbar from '../../../../common/bbar/Bbar';
import classes from './Chat.module.scss';
import ChatControl from './ChatControl';
import ChatSection from './ChatSection';

function Chat({ publishMessage }) {
  return (
    <Bbar>
      <div className={classes.chat_title}>
        <div className={classes.title_title}>채팅</div>
        <p>
          참여 중 말을 하기 어려울 때에는 채팅으로 친구들과 실시간으로
          소통해보세요.
        </p>
      </div>
      <br />
      <div className={classes.chat_body}>
        <ChatSection />
        {/* <hr /> */}
        <ChatControl publishMessage={publishMessage} />
      </div>
    </Bbar>
  );
}

export default Chat;
