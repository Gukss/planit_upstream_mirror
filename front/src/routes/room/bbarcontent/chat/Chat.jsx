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
        <p>채팅으로 친구들과 실시간으로 소통할 수 있습니다.</p>
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
