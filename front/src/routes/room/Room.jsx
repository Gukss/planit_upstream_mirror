import 'boxicons/css/boxicons.min.css';
import { Routes, Route, Outlet } from 'react-router-dom';
import RoomLayout from './RoomLayout';
import Search from './bbarcontent/search/Search';
import Tmp from '../Tmp';
import Place from './bbarcontent/place/Place';

function Room() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<RoomLayout />}>
          <Route path='search' element={<Search />} />
          <Route path='place' element={<Place />} />
          <Route path='schedule' element={<Tmp />} />
          <Route path='vote' element={<Tmp />} />
          <Route path='chat' element={<Tmp />} />
        </Route>
      </Routes>
      <Outlet />
    </div>
  );
}

export default Room;
