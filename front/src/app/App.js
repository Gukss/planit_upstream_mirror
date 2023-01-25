import './App.module.scss';
import { Routes, Route } from 'react-router-dom';
import Room from '../routes/room/Room';

function App() {
  return (
    <Routes>
      <Route path='/room/*' element={<Room />} />
    </Routes>
  );
}

export default App;
