import React, { Component } from 'react';
import APIResponse from "./APIResponse";
// Import the Log class...
import Log from './Log';


class QueryAPI extends Component {

    constructor(props) {
        super(props);
        this.state = { response: null };
    }

    authorizationHeader() {
        if(!this.props.keycloak) {
            Log.info('authorization header !', 'missing keycloak props');
            return {};
        } else {

            const myHeaders = new Headers();

            //myHeaders.set("Content-Type", 'application/json');
            myHeaders.set("Authorization", "Bearer " + this.props.keycloak.token);

            Log.info('authorization header !', 'sending token keycloak props ' + this.props.keycloak.token);
            return myHeaders;

        }
    }

    handleClick = () => {

        Log.info('Clicking... ', "just clicked...");

        const myInit = { method: 'GET',
            headers : this.authorizationHeader(),
            cache: 'default'};

        const myReq = new Request('http://localhost:89/customer', myInit);

        fetch(myReq, myInit)
            .then(response => {
                if (response.status === 200)
                    return response.json();
                else {
                    Log.debug("else status not 200", "message : " + response.statusText);
                    return {status: response.status, message: response.statusText}
                }
            })
            .then(json => this.setState((state, props) => ({
                response: JSON.stringify(json, null, 2)
            })))
            .catch(err => {
                Log.error("shit ! " + err.toString())
                this.setState((state, props) => ({ response: err.toString() }))
            })
    };

    render() {
        return (
            <div className="QueryAPI">
                <button onClick={this.handleClick}>Send API request</button>
                <APIResponse response={this.state.response}/>
            </div>
        );
    }
}

export default QueryAPI;