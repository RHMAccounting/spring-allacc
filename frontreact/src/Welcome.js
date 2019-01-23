import React, { Component } from 'react';
import QueryAPI from './QueryAPI';

class Welcome extends Component {
    render() {
        return (
            <div className="welcome">
                <p>Welcome here ! Click the following link to sign in...</p>
                <QueryAPI/>
            </div>
        );
    }
}
export default Welcome;