import React, { Component } from 'react';

export default class OpenViduVideoComponent extends Component {
  constructor(props) {
    super(props);
    this.videoRef = React.createRef();
  }

  componentDidMount() {
    console.log('VC-componentDidMount');
    if (this.props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  componentDidUpdate(props) {
    console.log('VC-componentDidUpdate');
    if (props && !!this.videoRef) {
      this.props.streamManager.addVideoElement(this.videoRef.current);
    }
  }

  render() {
    // eslint-disable-next-line jsx-a11y/media-has-caption
    return <video autoPlay ref={this.videoRef} />;
  }
}
