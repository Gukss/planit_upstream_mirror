import 'boxicons/css/boxicons.min.css';
import { Routes, Route, Outlet } from 'react-router-dom';
import RoomLayout from './RoomLayout';
import Search from './bbarcontent/search/Search';
import Tmp from '../Tmp';
import Place from './bbarcontent/place/Place';
import Schedule from './bbarcontent/schedule/Schedule';

function Room() {
  return (
    <div>
      <Routes>
        {/* 룸으로 들어가도 search로 바로 route 연결되게 하고싶음 */}
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route path='schedule' element={<Schedule />} />
          <Route path='vote' element={<Tmp />} />
          <Route path='chat' element={<Tmp />} />
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}

export default Room;
