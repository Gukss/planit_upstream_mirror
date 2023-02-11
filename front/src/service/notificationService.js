import axios from 'axios';

export const ServiceFindNotification = async member => {
  try {
    const success = await axios.get(`http://localhost:8080/api/notification`, {
      headers: {
        Authorization: member,
      },

      // 나중에는 베리어 형태로 바꿔주세요!
      // headers: {
      //   Authorization: `Bearer ${Login.userId}`,
      // },
    });

    return success;
  } catch (err) {
    return false;
  }
};
