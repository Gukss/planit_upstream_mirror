import { useRecoilValue } from 'recoil';
import classes from './ChatSection.module.scss';
import { chatMessages } from '../../../../app/store';

function ChatSection() {
  const messageList = useRecoilValue(chatMessages);
<<<<<<< HEAD
  // console.log(messageList);
  if (messageList.length >= 1) {
    return (
      <div className={classes.chat_section}>
        {messageList.map(messageListItem => {
          return <div>{messageListItem.message}</div>;
        })}
      </div>
    );
  }
=======
  console.log(messageList);
  return (
    <div className={classes.chat_section}>
      {messageList.map(messageListItem => {
        return (
          <div className={classes.chat_message_section}>
            {messageListItem.message}
          </div>
        );
      })}
    </div>
  );
>>>>>>> 30e8382671700a1771cf49d48f00a131798f14fb
}

export default ChatSection;
