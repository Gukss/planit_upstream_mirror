import './App.module.scss';
import { Routes, Route } from 'react-router-dom';
import Room from '../routes/room/Room';
import Login from '../routes/auth/login/Login';
import SignUp from '../routes/auth/signup/SignUp';

function App() {
  return (
    <Routes>
      <Route path='/room/*' element={<Room />} />
      <Route path='/login' element={<Login />} />
      <Route path='/signup' element={<SignUp />} />
    </Routes>
  );
}

export default App;
