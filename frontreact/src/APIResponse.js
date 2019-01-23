import React, { Component } from 'react';

class APIResponse extends Component {
    render() {
        if(this.props.response)
            return ( <pre>{this.props.response}</pre> );
        else
            return (<div/>);
    }
}

export default APIResponse;