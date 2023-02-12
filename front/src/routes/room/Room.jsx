import 'boxicons/css/boxicons.min.css';
import { Routes, Route, Outlet } from 'react-router-dom';
import RoomLayout from './RoomLayout';
import Search from './bbarcontent/search/Search';
import Place from './bbarcontent/place/Place';
import Schedule from './bbarcontent/schedule/Schedule';
import Vote from './bbarcontent/vote/Vote';
import Chat from './bbarcontent/chat/Chat';

function Room() {
  return (
    <div>
      <Routes>
        {/* 룸으로 들어가도 search로 바로 route 연결되게 하고싶음 */}
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route path='schedule' element={<Schedule />} />
          <Route path='vote' element={<Vote />} />
          <Route path='chat' element={<Chat />} />
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}

export default Room;

