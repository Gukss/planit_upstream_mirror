import React, { Component } from 'react';
import OpenViduVideoComponent from './OvVideo';
import classes from './UserVideoComponent.module.scss';

// eslint-disable-next-line react/prefer-stateless-function
export default class UserVideoComponent extends Component {
  // getNicknameTag() {
  //   // Gets the nickName of the user
  //   console.log('VC-getNicknameTag');
  //   return JSON.parse(this.props.streamManager.stream.connection.data)
  //     .clientData;
  // }

  render() {
    return (
      <div className={classes.videoContainer}>
        {this.props.streamManager !== undefined ? (
          <OpenViduVideoComponent streamManager={this.props.streamManager} />
        ) : null}
      </div>
    );
  }
}
