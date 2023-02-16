![Group2](https://user-images.githubusercontent.com/76477531/219307004-3bdb9e6f-1365-4d0a-b638-b4c3f0046dbb.svg)

## 📖목차
- [README](#readme)
	- [📖목차](#목차)
	- [📑 주제](#-주제)
	- [🎉 프로젝트 소개](#-프로젝트-소개)
		- [문제](#문제)
		- [해결방안](#해결방안)
	- [🔑 주요 기능](#-주요-기능)
	- [🖥 서비스 화면](#-서비스-화면)
	- [🎨 화면 설계서](#-화면-설계서)
		- [로그인](#로그인)
		- [회원가입](#회원가입)
		- [마이페이지](#마이페이지)
		- [일정](#일정)
		- [일정 만들기](#일정-만들기)
		- [여행장소 검색](#여행장소-검색)
		- [장소 추가](#장소-추가)
		- [일정에 추가](#일정에-추가)
		- [채팅](#채팅)
		- [투표](#투표)
	- [⚙ 아키텍쳐](#-아키텍쳐)
	- [📝 ERD](#-erd)
	- [🛠 주요 기술](#-주요-기술)
		- [백](#백)
		- [프론트](#프론트)
	- [🛠 협업 툴](#-협업-툴)
	- [🛠 협업 환경](#-협업-환경)
		- [Git](#git)
		- [Notion](#notion)
		- [Jira](#jira)
		- [Discord](#discord)
		- [Figma](#figma)
	- [📄 시연 시나리오](#-시연-시나리오)
		- [일정 만들기](#일정-만들기-1)
		- [키워드로 여행 장소 검색 후 "장소"에 추가](#키워드로-여행-장소-검색-후-장소에-추가)
		- [카테고리별 여행 장소 검색 후 "장소"에 추가](#카테고리별-여행-장소-검색-후-장소에-추가)
		- [일정에 추가하기](#일정에-추가하기)
		- [일정 조정하기](#일정-조정하기)
		- [일정 이미지로 추출하기](#일정-이미지로-추출하기)
		- [투표](#투표-1)
		- [채팅](#채팅-1)
		- [저장](#저장)
		- [진행 중인 여행 일정에 입장하기](#진행-중인-여행-일정에-입장하기)
		- [회원가입](#회원가입-1)
		- [로그인](#로그인-1)
	- [🗓 프로젝트 진행 기간](#-프로젝트-진행-기간)
	- [💾 포팅 매뉴얼](#-포팅-매뉴얼)
	- [❤ 팀원](#-팀원)
		- [프론트](#프론트-1)
		- [백](#백-1)

<!-- <small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small> -->

---

---

## 📑 주제

친구와 함께하는 여행계획 플랫폼

---

---

## 🎉 프로젝트 소개

### 문제

**코로나19가 엔데믹에 접어들어 여행을 가려는 사람의 수가 증가**하고 있다. 여행 계획을 짤 때 참여하여 의견을 제시하고 싶지만 **시간적, 공간적 여유가 되지않아 참여할 수 없는 사람**과 여행 날짜만 정하고 **세부계획은 아무도 작성하지 않아 혼자 여행계획을 작성하는 사람의 문제**가 있다. 또 **여행 계획을 작성할 때 여러가지 서비스를 사용해야하는 번거로움이 있다.**

### 해결방안

1. 같은 공간에 있지 않아도 **화상통화를 하며 함께 여행 계획을 작성**할 수 있다.
2. **일정 계획 과정을 실시간으로 공유**함으로 참여자들의 반응을 확인할 수 있다.
3. 여행 계획을 작성할 때 사용하는 **여러 서비스의 기능을 모아 한 번에 사용가능**하게 한다.

---

---

## 🔑 주요 기능

1. 같은 방에 속한 사람들과 화상통화 및 채팅
2. 키워드로 여행 장소 검색
3. 카테고리별 여행 장소 검색
4. 여행 장소를 지도에 표시하는 마커를 실시간 공유
5. 같은 방에 속한 사람들과 일정 계획 과정 실시간 공유
6. 작성한 일정을 이미지로 공유
7. 의사 결정을 위한 투표

---

---

## 🖥 서비스 화면

![일정검색](https://user-images.githubusercontent.com/76477531/219439998-930cd427-2a06-4641-aa9b-51d0bb8b4b2c.gif)
![채팅투표](https://user-images.githubusercontent.com/76477531/219439992-3c6cc475-ee23-4e6d-af8f-778d8de843fc.gif)
![일정변경](https://user-images.githubusercontent.com/76477531/219439982-ca1e29e1-8d6d-4c49-8f24-d1159b3007b8.gif)

---

---

## 🎨 화면 설계서

### 로그인

![signIn](https://user-images.githubusercontent.com/76477531/219292304-1d9bc4be-747c-4afa-8ba5-3303c5b3abf6.png)

---

### 회원가입

![signUp](https://user-images.githubusercontent.com/76477531/219304672-221e574a-72f7-4fa1-b766-48166573e309.png)

---

### 마이페이지

![myPage](https://user-images.githubusercontent.com/76477531/219304133-53796d53-7522-42dc-baf9-8c1eb88cdd21.png)

---

### 일정

![schedule](https://user-images.githubusercontent.com/76477531/219304214-791f45cc-99db-4436-a850-8a31ed32af73.png)

---

### 일정 만들기

![createRoom1](https://user-images.githubusercontent.com/76477531/219304263-4455936c-649f-4548-b2ca-467ba7766a8c.png)

![createRoom2](https://user-images.githubusercontent.com/76477531/219304331-50b3aa51-ca9e-49df-978e-c9c8d0b5855f.png)

---

### 여행장소 검색

![main1_search](https://user-images.githubusercontent.com/76477531/219304389-a7339faf-dd6e-48da-95f9-363ffead026b.png)

---

### 장소 추가

![main3_save](https://user-images.githubusercontent.com/76477531/219304538-505423d0-ee84-4824-b288-f9e2031c0c8a.png)

---

### 일정에 추가

![main2_confirm](https://user-images.githubusercontent.com/76477531/219304437-effa99f9-75a6-468b-92ee-ce3d43a3c91a.png)

---

### 채팅

![chatting](https://user-images.githubusercontent.com/76477531/219304583-2e13b84f-c986-4bf3-a139-c70ff87af194.png)

---

### 투표

![vote](https://user-images.githubusercontent.com/76477531/219304630-ea54cee7-4d01-4b88-9c90-e2fa10ce4fbe.png)

---

---

## ⚙ 아키텍쳐

![시스템아키텍쳐](https://user-images.githubusercontent.com/76477531/219374775-ee6d8400-cd60-4982-be07-adebd8f4e1f7.png)

---

---

## 📝 ERD

![PLANIT (1)](https://user-images.githubusercontent.com/76477531/219305960-8e527370-6f55-44cd-a47e-714b18cb4aa6.png)

---

---

## 🛠 주요 기술

### 백

- openJDK 8
- IntelliJ IDE
- Springboot 2.7.7
- Spring Data JPA
- Spring Security
- Spring Web
- WebSocket: Stomp
- MySql: 5.7.30

### 프론트

- VsCode
- Node.js 16.19.0
- React 18.2.0
- SASS

---

---

## 🛠 협업 툴

- Git
- Notion
- Jira
- Discord
- Figma

---

---

## 🛠 협업 환경

### Git

- 코드 버전을 관리

### Notion

- 회의가 있을 때 마다 회의록을 기록해 보관
- 기술 공부 시 문서를 작성해 팀원과 공유
- 같은 버그 발생 시 빠른 해결을 위해 디버깅 내역을 기록
- 기능명세서, ERD, REST API 등 모두가 공유해야하는 문서 관리
- 컨벤션 정리
- 간트차트 관리

### Jira

- 매주 목표량을 설정해 Sprint 진행
- 업무의 할당량을 정해 Story Point를 설정하고, In-Progress → Done 순으로 작업

### Discord

- 화면 공유로 팀원간 원활한 비대면 소통

### Figma

- 화면 설계서 작성

---

---

## 📄 시연 시나리오

### 일정 만들기

![일정만들기1](https://user-images.githubusercontent.com/76477531/219437807-d37cd9de-e310-4f62-85d8-5382b5698a67.JPG)
![일정만들기2](https://user-images.githubusercontent.com/76477531/219437818-8a0d85b6-acd1-426b-b983-1cd129b77c12.JPG)
![일정만들기3](https://user-images.githubusercontent.com/76477531/219437819-bd8ee00f-0395-4047-af7c-272a7b1c53ff.JPG)
![일정만들기4](https://user-images.githubusercontent.com/76477531/219437822-aa84ce6e-8d09-475e-8a7a-f09f4b9ec637.JPG)

1. 화면 우측 상단 “일정 만들기” 버튼 또는 메인 페이지의 “바로 시작하기” 버튼을 클릭한다.
2. 여행일자를 선택하고 일정제목을 입력한다.
3. 로고 밑의 토글 버튼을 클릭해 친구초대 창으로 전환한다.
4. 친구의 아이디를 입력한다.
5. 출력되는 친구의 계정 옆의 “+” 버튼을 클릭해 일정을 생성할 때 친구를 초대할 수 있다.
6. 화면 하단의 “방 생성하기” 버튼을 클릭해 일정을 생성하고 일정 계획 화면으로 전환된다.

---

### 키워드로 여행 장소 검색 후 "장소"에 추가

![일정검색1](https://user-images.githubusercontent.com/76477531/219437948-d8bb0d2d-cf55-456b-90e7-1836ab816d20.JPG)
![일정검색2](https://user-images.githubusercontent.com/76477531/219437962-501cad9c-92a9-4dc5-90a3-f93acee211a1.JPG)
![일정검색3](https://user-images.githubusercontent.com/76477531/219437967-9dae7533-709d-4b9b-ba86-eee850b57f95.JPG)

1. 왼쪽 메뉴 바에서 “검색”버튼을 클릭해 검색할 수 있다.
2. 검색어를 입력하고 “Enter”를 클릭하면 연관된 장소가 카드 형식과 지도에 마커로 출력된다.
   1. 장소 카드를 클릭하면 해당 장소의 위치로 지도가 이동한다.
   2. 장소 카드의 “가게 정보 확인하기” 버튼을 누르면 가게 정보를 확인할 수 있다.
3. 장소 카드의 오른쪽 위의 “+” 버튼을 클릭하거나 마커를 클릭한 후 “장소추가” 버튼을 클릭해 장소를 추가할 수 있다.
4. 추가된 장소는 왼쪽 메뉴 바에서 “장소” 버튼을 클릭해 확인 할 수 있다.
5. 변경된 사항은 참여자들에게 모두 실시간 공유된다.

---

### 카테고리별 여행 장소 검색 후 "장소"에 추가

![일정검색4_카테고리](https://user-images.githubusercontent.com/76477531/219437972-76d57254-5967-45ee-a6fc-4c2de55eb6a4.JPG)

1. 지도 오른쪽 상단의 카테고리를 클릭하면 현재 지도에서 카테고리에 해당하는 장소가 마커로 출력된다.
2. 장소 카드의 오른쪽 위의 “+” 버튼을 클릭하거나 마커를 클릭한 후 “장소추가” 버튼을 클릭해 장소를 추가할 수 있다.
3. 추가된 장소는 왼쪽 메뉴 바에서 “장소” 버튼을 클릭해 확인 할 수 있다.
4. 변경된 사항은 참여자들에게 모두 실시간 공유된다.

---

### 일정에 추가하기

![일정추가1](https://user-images.githubusercontent.com/76477531/219438063-821f2be5-0618-4974-9028-7103e1257f6a.JPG)
![일정추가2](https://user-images.githubusercontent.com/76477531/219438070-e482d1e2-1eb8-45c1-95aa-57c1f9e244df.JPG)

1. 왼쪽 메뉴 바에서 “장소” 버튼을 클릭해 현재 담긴 장소를 확인할 수 있다.
   1. 장소는 카테고리 별로 분리되어 있다.
2. 담긴 장소 태그의 색깔 부분을 클릭하면 지도가 해당하는 장소 위치로 이동한다.
3. 담긴 장소 태그의 이름 부분을 클릭하면 해당 장소가 일정에 추가된다.
4. 변경된 사항은 참여자들에게 모두 실시간 공유된다.

---

### 일정 조정하기

![일정조정1](https://user-images.githubusercontent.com/76477531/219438140-99a9af47-890d-4552-969b-c49bdc34ca30.JPG)
![일정조정2](https://user-images.githubusercontent.com/76477531/219438152-f3a7efa9-4a72-46a8-8c33-8b13bc4e76b7.JPG)

1. 추가된 일정을 원하는 날짜 박스로 드래그해서 일정을 조정할 수 있다.
2. 변경된 사항은 참여자들에게 모두 실시간 공유된다.

---

### 일정 이미지로 추출하기

![일정내보내기](https://user-images.githubusercontent.com/76477531/219438291-599313eb-1477-4147-b808-980a8b771cf7.JPG)

1. 왼쪽 메뉴 바의 “일정” 에서만 이미지로 추출이 가능하다.
2. 왼쪽 메뉴 바의 오른쪽 상단 “공유하기” 버튼을 눌러 이미지로 추출한다.

---

### 투표

![투표1](https://user-images.githubusercontent.com/76477531/219438400-f89a42c0-fb2f-48af-9751-6436ada9c5b6.JPG)
![투표2](https://user-images.githubusercontent.com/76477531/219438409-8efb00a9-c332-413a-99a9-a0692e4cf023.JPG)

1. 왼쪽 메뉴 바의 “투표” 버튼을 클릭한다.
2. “투표 내역” 글씨 옆의 “+” 버튼을 눌러 투표를 생성할 수 있다.
3. 투표 제목과 항목을 입력하고 “OK” 버튼을 눌러 투표를 생성할 수 있다.
4. 항목을 선택하고 “투표하기” 버튼을 클릭하여 투표를 할 수 있다.

---

### 채팅

![채팅](https://user-images.githubusercontent.com/76477531/219438605-f9793011-483e-49ef-a78c-22263c65cbce.JPG)

1. 참여자들과 채팅을 할 수 있다.

---

### 저장

1. 화면 상단 가운데 “저장” 버튼을 클릭해 작업한 상황을 저장할 수 있다.

---

### 진행 중인 여행 일정에 입장하기

![진행중인일정입장](https://user-images.githubusercontent.com/76477531/219438689-00feec2b-9dc4-4cb8-be93-ec49cf6cac72.JPG)

1. 메인 페이지에서 오른쪽 상단의 프로필을 누르면 마이페이지로 이동한다.
2. “진행 중인 여행 일정” 에서 일정 카드의 “입장” 버튼을 누르면 진행 중인 여행 일정에 입장한다.

---

### 회원가입

![회원가입1](https://user-images.githubusercontent.com/76477531/219373058-c30644ef-78eb-477a-ad10-e5000a6e4337.JPG)
![회원가입2](https://user-images.githubusercontent.com/76477531/219373064-7c09232b-a531-487e-b848-4cd896c5f0ce.JPG)
![회원가입3](https://user-images.githubusercontent.com/76477531/219373065-4533badc-872d-4b7b-bf6c-23ab64f9ff66.JPG)

1. 아이디를 입력하고 “확인” 버튼을 눌러 아이디 중복 체크를 한다.
2. 나머지 입력 사항을 모두 입력하고 가입하기 버튼을 누르면 가입이 완료된다.

---

### 로그인

![로그인](https://user-images.githubusercontent.com/76477531/219373117-86a6b32e-ae57-4831-82cd-30c1129da8a3.JPG)

1. 아이디와 비밀번호를 입력하고 “로그인” 버튼을 누르면 로그인이 완료된다.

---

---

## 🗓 프로젝트 진행 기간

`2023.01.03 ~ 2023.02.17 (약 7주)`

---

---

## 💾 포팅 매뉴얼

| 포트        | 유형    | 프로그램         | 사용포트내용                                      |
| ----------- | ------- | ---------------- | ------------------------------------------------- |
| 22          | TCP     | SSH              | Ubuntu 접속을 위해                                |
| 80          | TCP     | HTTP             | HTTP 기본 Port                                    |
| 443         | TCP     | HTTPS            | HTTPS 기본 Port                                   |
| 3000        | TCP     | DOCKER, REACT    | planit_front_container의 react port               |
| 3478        | TCP/UDP | DOCKER, OpenVidu | 클라이언트 IP 확인을 위해 STUN/TURN 서버에서 사용 |
| 8080        | TCP     | DOCKER, Spring   | planit_back_container의 Spring Port               |
| 8443        | TCP     | DOCKER, OpenVidu | OpenVidu Client-side                              |
| 9090        | TCP     | DOCKER, Jenkins  | Jenkins Port(8080 → 9090)                         |
| 9091        | TCP     | DOCKER, Jenkins  | SSL 인증 Jenkins(9090 → 9091)                     |
| 40000:57000 | TCP/UDP | DOCKER, OpenVidu | Kurento media Server에서 미디어 연결 설정 시 사용 |
| 57001:65535 | TCP/UDP | DOCKER, OpenVidu | TURN 서버에서 릴레이 된 미디어 연결 설정 시 사용  |

---

---

## ❤ 팀원

### 프론트

[eondo - Overview](https://github.com/eondo)

[dongminYOUN - Overview](https://github.com/dongminYOUN)

[jonghopark1014 - Overview](https://github.com/jonghopark1014)

### 백

[hagnoykmik - Overview](https://github.com/hagnoykmik)

[Gukss - Overview](https://github.com/Gukss)

[sksn12 - Overview](https://github.com/sksn12)
