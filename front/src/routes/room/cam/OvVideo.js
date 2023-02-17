import React, { Component } from 'react';

export default class OpenViduVideoComponent extends Component {
  constructor(props) {
    super(props);
    this.videoRef = React.createRef();
  }

  componentDidMount() {
    console.log('OV-1');
    console.log(this.props.streamManager);
    if (this.props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  componentDidUpdate(props) {
    console.log('OV-2');
    if (props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  render() {
    // eslint-disable-next-line jsx-a11y/media-has-caption
    return <video autoPlay ref={this.videoRef} style={{ height: '100%' }} />;
  }
}
