# Dockerfile

FROM node:16.19.0 as builder
# root 에 app 폴더를 생성

WORKDIR /app

COPY . /app

RUN npm install

RUN npm run build

# work dir 에 build 폴더 생성 /app/build

# host pc의 현재경로의 build 폴더를 workdir 의 build 폴더로 복사
# nginx 이미지를 사용합니다. 뒤에 tag가 없으면 latest 를 사용합니다.
FROM nginx

# nginx 의 default.conf 를 삭제
RUN rm /etc/nginx/conf.d/default.conf

# host pc 의 nginx.conf 를 아래 경로에 복사
COPY /etc/nginx/nginx.conf /etc/nginx/conf.d

COPY --from=builder /app/build /usr/share/nginx/html

# 80 포트 오픈
EXPOSE 80

# container 실행 시 자동으로 실행할 command. nginx 시작함
CMD ["nginx", "-g", "daemon off;"]
