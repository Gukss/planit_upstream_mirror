import './App.module.scss';
import { Routes, Route } from 'react-router-dom';
import Room from '../routes/room/Room';
import Login from '../routes/auth/login/Login';

function App() {
  return (
    <Routes>
      <Route path='/room/*' element={<Room />} />
      <Route path='/login' element={<Login />} />
    </Routes>
  );
}

export default App;
