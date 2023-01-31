import './App.module.scss';
import { Routes, Route } from 'react-router-dom';
import HomeLayout from '../routes/home/HomeLayout';
import Room from '../routes/room/Room';
import Login from '../routes/auth/login/Login';
import SignUp from '../routes/auth/signup/SignUp';
import CreateRoom from '../routes/home/create/CreateRoom';
import MyPage from '../routes/auth/mypage/MyPage';

function App() {
  return (
    <Routes>
      <Route path='/' element={<HomeLayout />} />
      <Route path='/room/*' element={<Room />} />
      <Route path='/login' element={<Login />} />
      <Route path='/signup' element={<SignUp />} />
      <Route path='/createroom' element={<CreateRoom />} />
      <Route path='/mypage' element={<MyPage />} />
    </Routes>
  );
}

export default App;
