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

## 프로젝트 빌드

### 백

- 로컬: back/src/resources/application.yml을 아래 내용으로 수정 후 실행

```
server:
  port: 8080
  error:
    whitelabel:
      enabled: false
    servlet:
      context-path: /api

spring:
  # TODO : PUSH 하기 전에 다시 한번 확인할 것.
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/planit?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: 1234

  mvc:
    dispatch-options-request: true

  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    open-in-view: false
    show-sql: true
    hibernate:
      format_sql: true
      #      ddl-auto: create
      ddl-auto: none

  #socket test
  devtools:
    livereload:
      enabled: true
    restart:
      enabled: false
  freemarker:
    cache: false

logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
      springframework:
        boot:
          autoconfigure: info
jwt:
  header: Authorization
  secret: PlanitplanitPlanitplanitPlanitplanitPlaintplanitPlanitplanitPlaint
  #  30분 1000L * 30 * 60
  access-token-validity-in-seconds: 1800000
  #  일주일 1000L * 60 * 60 * 24 * 7
  refresh-token-validity-in-seconds: 604800000
```

- 서버: application.yml 파일을 아래 내용으로 수정 후 실행
    - DB_DOMAIN: DB 서버 도메인
    - DB_PORT: DB 서버 포트
    - DB_USERNAME: DB 유저 이름
    - DB_PASSWORD: DB 유저 비밀번호

```
server:
  port: 8080
  error:
    whitelabel:
      enabled: false
  servlet:
    context-path: /api

spring:
  # TODO : PUSH 하기 전에 다시 한번 확인할 것.
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://${DB_DOMAIN}:${DB_PORT}/planit?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  mvc:
    dispatch-options-request: true

  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    open-in-view: false
    show-sql: true
    hibernate:
      format_sql: true
#      ddl-auto: create
      ddl-auto: none

  #socket test
  devtools:
    livereload:
      enabled: true
    restart:
      enabled: false
  freemarker:
    cache: false

logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
      springframework:
        boot:
          autoconfigure: info
jwt:
  header: Authorization
  secret: PlanitplanitPlanitplanitPlanitplanitPlaintplanitPlanitplanitPlaint
  #  30분 1000L * 30 * 60
  access-token-validity-in-seconds: 1800000
  #  일주일 1000L * 60 * 60 * 24 * 7
  refresh-token-validity-in-seconds: 604800000
```

### 프론트

- 최상위 폴더 (/)에 .env 파일 생성 후 카카오API키 입력

```
REACT_APP_KAKAO_API_KEY = '7c01e88163ab50f09cc5f765bf7f5037'
```

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

