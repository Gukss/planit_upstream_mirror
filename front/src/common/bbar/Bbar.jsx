import React, { useEffect, useState } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import axios from 'axios';
import domtoimage from 'dom-to-image';
import { saveAs } from 'file-saver';
import Swal from 'sweetalert2';
import { userInfoState, roomInfoState } from '../../app/store';
import './Bbar.scss';

function Bbar(props) {
  // 일정 카카오톡 공유
  // const createKakaoButton = () => {
  //   if (window.Kakao) {
  //     // 카카오 스크립트가 로드된 경우 init
  //     const kakao = window.Kakao;
  //     // if (!kakao.isInitialized()) {
  //     //   kakao.init('7c01e88163ab50f09cc5f765bf7f5037');
  //     // }
  //     kakao.Share.createDefaultButton({
  //       container: '#kakaotalk-sharing-btn',
  //       objectType: 'feed',
  //       content: {
  //         title: 'planit',
  //         description: '#test_content',
  //         // imageUrl 이 없으면 동작 안하기 때문에 default 이미지를 준비해 두기
  //         imageUrl: 'https://cdn-icons-png.flaticon.com/512/3448/3448653.png',
  //         link: {
  //           mobileWebUrl: 'http://localhost:3000',
  //           webUrl: 'http://localhost:3000',
  //         },
  //       },
  //     });
  //   }
  // };

  // 일정 이미지로 저장 코드
  const userInfo = useRecoilValue(userInfoState);
  const roomInfo = useRecoilValue(roomInfoState);

  const instance = axios.create({
    baseURL: 'https://i8b202.p.ssafy.io/api',
    headers: {
      Authorization: `Bearer ${userInfo.token}`,
      contentType: 'application/json',
    },
  });

  const title = 'planit';

  const onDownloadBtn = () => {
    domtoimage
      .toBlob(document.querySelector('.schedulebox_section'))
      // schedulebox_section
      .then(blob => {
        Swal.fire({
          title: '일정 내보내기',
          text: '해당 일정을 이미지 파일로 내보내겠습니까?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Export',
        }).then(result => {
          if (result.isConfirmed) {
            Swal.fire({
              title: '🚀 행복한 여행 보내세요!',
              text: 'PLAN WITH PLAN!T',
              imageUrl:
                'https://cdn.pixabay.com/animation/2022/07/31/20/14/20-14-05-792_512.gif',
              imageHeight: 200,
              timer: 3000,
              showConfirmButton: false,
            });

            saveAs(blob, `${title}.png`);
          }
        });
      });
  };

  return (
    <div className='bbar'>
      <div className='bbar_container'>
        <div className='bbar__header'>
          <div className='bbar__header__title'>{roomInfo.roomName}</div>
          <div className='bbar__header__date'>
            <i className='bx bxs-plane-alt'></i>
            {roomInfo.startDate} ~ {roomInfo.endDate}
          </div>
        </div>
        {props.children[0].props.className === 'schedule_title' && (
          <i
            className='bx bx-export'
            onClick={onDownloadBtn}
            onKeyDown={onDownloadBtn}
            role='button'
            tabIndex={0}
            aria-label='export'
          />
        )}
      </div>
      <hr></hr>

      {/* <button id='kakaotalk-sharing-btn' onClick={createKakaoButton}>
        <img
          src='https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png'
          alt='카카오톡 공유 보내기 버튼'
        />
      </button> */}

      <div className='bbar__menu'>{props.children}</div>
    </div>
  );
}

export default Bbar;
