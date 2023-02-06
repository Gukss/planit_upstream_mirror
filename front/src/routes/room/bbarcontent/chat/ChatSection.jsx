import { useRecoilValue } from 'recoil';
import classes from './ChatSection.module.scss';
import { chatMessages } from '../../../../app/store';

function ChatSection() {
  const messageList = useRecoilValue(chatMessages);
  return (
    <div className={classes.chat_section}>
      {messageList.map(messageListItem => {
        return <div>{messageListItem.message}</div>;
      })}
    </div>
  );
}

export default ChatSection;
