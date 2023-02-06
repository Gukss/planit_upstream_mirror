/* eslint-disable no-return-await */
/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/label-has-associated-control */
import { OpenVidu } from 'openvidu-browser';

import axios from 'axios';
import React, { Component } from 'react';
import UserVideoComponent from './UserVideoComponent';

const OPENVIDU_SERVER_URL = 'https://i8b202.p.ssafy.io:8443';
const OPENVIDU_SERVER_SECRET = 'MY_SECRET';

class VideoRoomComponent extends Component {
  constructor(props) {
    super(props);

    // These properties are in the state's component in order to re-render the HTML whenever their values change
    this.state = {
      // 참가 세션 id
      mySessionId: 'SessionA',
      // 참가자 이름
      myUserName: Math.floor(Math.random() * 100),
      // 세션
      session: undefined,
      mainStreamManager: undefined, // Main video of the page. Will be the 'publisher' or one of the 'subscribers'
      // 자체 로컬 웹캠 스트림
      publisher: undefined,
      // 다른 사용자의 활성 스트림을 저장
      subscribers: [],
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.switchCamera = this.switchCamera.bind(this);
    this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
  }

  componentDidMount() {
    console.log('VC-componentDidMount');
    window.addEventListener('beforeunload', this.onbeforeunload);
  }

  componentWillUnmount() {
    console.log('VC-componentWillUnmount');
    window.removeEventListener('beforeunload', this.onbeforeunload);
  }

  handleChangeSessionId(e) {
    console.log('VC-handleChangeSessionId');
    console.log('-----------');
    console.log(e.target.value);
    this.setState({
      mySessionId: e.target.value,
    });
  }

  handleChangeUserName(e) {
    console.log('VC-handleChangeUserName');
    this.setState({
      myUserName: e.target.value,
    });
  }

  handleMainVideoStream(stream) {
    console.log('VC-handleMainVideoStream');
    if (this.state.mainStreamManager !== stream) {
      this.setState({
        mainStreamManager: stream,
      });
    }
  }

  onbeforeunload(event) {
    console.log('VC-onbeforeunload');
    this.leaveSession();
  }

  // eslint-disable-next-line react/sort-comp
  deleteSubscriber(streamManager) {
    console.log('VC-deleteSubscriber');
    const { subscribers } = this.state;
    const index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      this.setState({
        subscribers,
      });
    }
  }

  joinSession() {
    console.log('VC-joinSession');
    // --- 1) Get an OpenVidu object ---
    // openvidu 개체 가져오기
    this.OV = new OpenVidu();

    // --- 2) Init a session ---
    // 세션 초기화
    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        // 세션 이벤트를 읽음
        const mySession = this.state.session;
        console.log(mySession);

        // --- 3) 세션에서 이벤트가 발생할 때 수행할 작업 지정 ---

        // On every new Stream received...
        // streamCreated: session이 수신한 각각의 새 Stream에 대해 구독하고 반환된 구독자 개체를 subscriber 배열에 저장함
        mySession.on('streamCreated', event => {
          // Subscribe to the Stream to receive it. Second parameter is undefined
          // so OpenVidu doesn't create an HTML video by its own
          const subscriber = mySession.subscribe(event.stream, undefined);
          const { subscribers } = this.state;
          subscribers.push(subscriber);

          // Update the state with the new subscribers
          this.setState({
            subscribers,
          });
        });

        // On every Stream destroyed...
        mySession.on('streamDestroyed', event => {
          // Remove the stream from 'subscribers' array
          this.deleteSubscriber(event.stream.streamManager);
        });

        // On every asynchronous exception...
        mySession.on('exception', exception => {
          console.warn(exception);
        });

        // --- 4) Connect to the session with a valid user token ---

        // Get a token from the OpenVidu deployment
        this.getToken().then(token => {
          //  첫 번째 매개 변수는 OpenVidu 배포에서 얻은 토큰입니다. 이벤트 시 모든 사용자가 두 번째 매개 변수를 검색할 수 있음
          // 'streamCreated'(속성 Stream.connection.data)이며 사용자의 닉네임으로 DOM에 추가됩니다
          mySession
            .connect(token, { clientData: this.state.myUserName })
            .then(async () => {
              // --- 5) Get your own camera stream ---

              // targetElement로 정의되지 않은 게시자를 삽입합니다(OpenVidu가 비디오 요소를 삽입하는 것을 원하지 않습니다. 우리는 그것을 우리 스스로 관리할 것입니다). 그리고 원하는 속성을 가지고 있습니다
              const publisher = await this.OV.initPublisherAsync(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                publishVideo: true, // Whether you want to start publishing with your video enabled or not
                resolution: '640x480', // The resolution of your video
                frameRate: 30, // The frame rate of your video
                insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
                mirror: false, // Whether to mirror your local video or not
              });

              // --- 6) Publish your stream ---

              mySession.publish(publisher);

              // 현재 사용 중인 비디오 장치 가져오기
              const devices = await this.OV.getDevices();
              const videoDevices = devices.filter(
                device => device.kind === 'videoinput'
              );
              const currentVideoDeviceId = publisher.stream
                .getMediaStream()
                .getVideoTracks()[0]
                .getSettings().deviceId;
              const currentVideoDevice = videoDevices.find(
                device => device.deviceId === currentVideoDeviceId
              );

              // 웹캠을 표시하고 게시자를 저장하도록 페이지의 기본 비디오 설정
              this.setState({
                currentVideoDevice,
                mainStreamManager: publisher,
                publisher,
              });
            })
            .catch(error => {
              console.log(
                'There was an error connecting to the session:',
                error.code,
                error.message
              );
            });
        });
      }
    );
  }

  leaveSession() {
    console.log('VC-leaveSession');
    // --- 7) Leave the session by calling 'disconnect' method over the Session object ---

    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
    }

    // Empty all properties...
    this.OV = null;
    this.setState({
      session: undefined,
      subscribers: [],
      mySessionId: 'SessionA',
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
      mainStreamManager: undefined,
      publisher: undefined,
    });
  }

  async switchCamera() {
    console.log('VC-switchCamera');
    try {
      const devices = await this.OV.getDevices();
      const videoDevices = devices.filter(
        device => device.kind === 'videoinput'
      );

      if (videoDevices && videoDevices.length > 1) {
        const newVideoDevice = videoDevices.filter(
          device => device.deviceId !== this.state.currentVideoDevice.deviceId
        );

        if (newVideoDevice.length > 0) {
          // 특정 videoSource를 사용하여 새 게시자 만들기
          // 모바일 장치에서는 기본 카메라와 첫 번째 카메라가 전면 카메라입니다
          const newPublisher = this.OV.initPublisher(undefined, {
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: true,
            publishVideo: true,
            mirror: true,
          });

          // newPublisher.once("accessAllowed", () => {
          await this.state.session.unpublish(this.state.mainStreamManager);

          await this.state.session.publish(newPublisher);
          this.setState({
            currentVideoDevice: newVideoDevice[0],
            mainStreamManager: newPublisher,
            publisher: newPublisher,
          });
        }
      }
    } catch (e) {
      console.error(e);
    }
  }

  render() {
    const { mySessionId } = this.state;
    const { myUserName } = this.state;

    return (
      <div className='container'>
        {this.state.session === undefined ? (
          <div id='join'>
            <div id='img-div'>
              <img
                src='resources/images/openvidu_grey_bg_transp_cropped.png'
                alt='OpenVidu logo'
              />
            </div>
            <div id='join-dialog' className='jumbotron vertical-center'>
              <h1> Join a video session </h1>
              <form className='form-group' onSubmit={this.joinSession}>
                <p>
                  <label>Participant: </label>
                  <input
                    className='form-control'
                    type='text'
                    id='userName'
                    value={myUserName}
                    onChange={this.handleChangeUserName}
                    required
                  />
                </p>
                <p>
                  <label> Session: </label>
                  <input
                    className='form-control'
                    type='text'
                    id='sessionId'
                    value={mySessionId}
                    onChange={this.handleChangeSessionId}
                    required
                  />
                </p>
                <p className='text-center'>
                  <input
                    className='btn btn-lg btn-success'
                    name='commit'
                    type='submit'
                    value='JOIN'
                  />
                </p>
              </form>
            </div>
          </div>
        ) : null}

        {this.state.session !== undefined ? (
          <div id='session'>
            <div id='session-header'>
              <h1 id='session-title'>{mySessionId}</h1>
              <input
                className='btn btn-large btn-danger'
                type='button'
                id='buttonLeaveSession'
                onClick={this.leaveSession}
                value='Leave session'
              />
              <input
                className='btn btn-large btn-success'
                type='button'
                id='buttonSwitchCamera'
                onClick={this.switchCamera}
                value='Switch Camera'
              />
            </div>

            {this.state.mainStreamManager !== undefined ? (
              <div id='main-video' className='col-md-6'>
                <UserVideoComponent
                  streamManager={this.state.mainStreamManager}
                />
              </div>
            ) : null}
            <div id='video-container' className='col-md-6'>
              {this.state.publisher !== undefined ? (
                <div
                  className='stream-container col-md-6 col-xs-6'
                  onClick={() =>
                    this.handleMainVideoStream(this.state.publisher)
                  }
                >
                  <UserVideoComponent streamManager={this.state.publisher} />
                </div>
              ) : null}
              {this.state.subscribers.map((sub, i) => (
                <div
                  key={sub.id}
                  className='stream-container col-md-6 col-xs-6'
                  onClick={() => this.handleMainVideoStream(sub)}
                >
                  <span>{sub.id}</span>
                  <UserVideoComponent streamManager={sub} />
                </div>
              ))}
            </div>
          </div>
        ) : null}
      </div>
    );
  }

  /**
   * --------------------------------------------
   * 응용 프로그램 서버에서 토큰 가져오기
   * --------------------------------------------
   * 아래 방법은 응용프로그램 서버에 대한 세션 및 토큰 작성을 요청합니다. 이렇게 하면 OpenVidu 배포가 안전하게 유지됩니다.
   *
   * 이 샘플 코드에는 사용자 제어가 전혀 없습니다. 누구나 응용프로그램 서버 엔드포인트에 액세스할 수 있습니다! 실제 프로덕션 환경에서 애플리케이션 서버는 엔드포인트에 대한 액세스를 허용할 사용자를 식별해야 합니다.
   *
   * 애플리케이션 서버의 OpenVidu 통합에 대해 자세히 알아보려면 https://docs.openvidu.io/en/stable/application-server을 방문하십시오.
   */
  getToken() {
    console.log('VC-getToken');
    return this.createSession(this.state.mySessionId).then(sessionId =>
      this.createToken(sessionId)
    );
  }

  // eslint-disable-next-line class-methods-use-this
  createSession(sessionId) {
    console.log('VC-createSession');
    return new Promise((resolve, reject) => {
      const data = JSON.stringify({ customSessionId: sessionId });

      axios
        .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, data, {
          headers: {
            Authorization: `Basic ${btoa(
              `OPENVIDUAPP:${OPENVIDU_SERVER_SECRET}`
            )}`,
            'Content-Type': 'application/json',
          },
        })
        .then(res => {
          resolve(res.data.id);
        })
        .catch(res => {
          const error = { ...res };

          if (error?.response?.status === 409) {
            resolve(sessionId);
          } else if (
            window.confirm(
              `No connection to OpenVidu Server. This may be a certificate error at "${OPENVIDU_SERVER_URL}"\n\nClick OK to navigate and accept it. If no certifica` +
                `te warning is shown, then check that your OpenVidu Server is up and running at` +
                ` "${OPENVIDU_SERVER_URL}"`
            )
          ) {
            window.location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
          }
        });
    });
  }

  // eslint-disable-next-line class-methods-use-this
  createToken(sessionId) {
    console.log('VC-createToken');
    return new Promise((resolve, reject) => {
      const data = {};

      axios
        .post(
          `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
          data,
          {
            headers: {
              Authorization: `Basic ${btoa(
                `OPENVIDUAPP:${OPENVIDU_SERVER_SECRET}`
              )}`,
              'Content-Type': 'application/json',
            },
          }
        )
        .then(res => {
          resolve(res.data.token);
        })
        .catch(error => reject(error));
    });
  }
}

export default VideoRoomComponent;
