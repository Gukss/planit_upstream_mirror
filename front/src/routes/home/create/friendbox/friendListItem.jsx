import './friendListItem.scss';

function FriendListItem(props) {
  return <div className='friend_name'>{props.user}</div>;
}

export default FriendListItem;
