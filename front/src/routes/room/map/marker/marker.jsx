import React from 'react';
// recoil state에 저장된 값으로 search 정보를 가져옴

function marker() {
  return (
    <div class='wrap'>
      <div class='info'>
        <div class='title'>
          카카오 스페이스닷원
          <div class='close' onclick='closeOverlay()' title='닫기'></div>
        </div>
        <div class='body'>
          <div class='img'>
            <img
              src='https://cfile181.uf.daum.net/image/250649365602043421936D'
              width='73'
              height='70'
            />
          </div>
          <div class='desc'>
            <div class='ellipsis'>제주특별시 제주시 첨단로 242</div>
            <div class='jibun ellipsis'>(우) 63309 (지번) 영평동 2181</div>
            <div>
              <a
                href='https://www.kakaocorp.com/main'
                target='_blank'
                class='link'
              >
                홈페이지
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default marker;
