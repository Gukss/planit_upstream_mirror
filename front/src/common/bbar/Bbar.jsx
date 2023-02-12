import React from 'react';
import domtoimage from 'dom-to-image';
import { saveAs } from 'file-saver';
import Swal from 'sweetalert2';
import './Bbar.scss';

function Bbar(props) {
  const title = 'planit';

  const onDownloadBtn = () => {
    domtoimage
      .toBlob(document.querySelector('.Schedule_schedulebox_section__ITIpX'))
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
              title: '행복한 여행 보내세요!',
              imageUrl:
                'https://cdn.pixabay.com/animation/2022/07/31/20/14/20-14-05-792_512.gif',
              imageHeight: 300,
              timer: 3000,
              showConfirmButton: false,
            });
            saveAs(blob, `${title}.png`);
          }
        });
      });

    // .then(blob => {
    //   saveAs(blob, `${title}.png`);
    // });
  };

  return (
    <div className='bbar'>
      <div className='bbar_container'>
        <div className='bbar__header'>
          <div className='bbar__header__title'>제목</div>
          <div className='bbar__header__date'>일정 : 2023.01.14</div>
        </div>
        <i
          className='bx bx-export'
          onClick={onDownloadBtn}
          onKeyDown={onDownloadBtn}
          role='button'
          tabIndex={0}
          aria-label='export'
        />
      </div>

      <hr />

      <div className='bbar__menu'>{props.children}</div>
    </div>
  );
}

export default Bbar;
