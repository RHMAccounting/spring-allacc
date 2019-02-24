import React, { Component } from 'react';
import './App.css';
import AppNavBar from './AppNavBar';
import {  Container, Button, Jumbotron, Row, Col, Form, FormGroup, InputGroup, Input, Label} from 'reactstrap';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';

class User extends Component {

    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state = {csrfToken: cookies.get('XSRF-TOKEN'),
            isLoading: true,isAuthenticated: false, user:undefined, details: null};

        this.edit = this.edit.bind(this);
        this.update = this.update.bind(this);
    }


    async componentDidMount() {

        this.setState({isLoading: true});

        try {
            const response = await fetch('/api/user/signin', {credentials: 'include'});

            if (!response.ok) {
                throw Error(response.statusText);
            }

            const json = await response.json();

            if (json === '') {
                this.setState(({isAuthenticated: false}))
            } else {
                console.log(json);
                this.setState({isAuthenticated: true, user: json, isLoading: false});
            }
        } catch(error) {
            console.log(error);
        }
    }

    /**
     * Editing user details to update
     */
    edit() {

        if (this.state.user !== undefined)
            this.setState({details:
                <Form>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="email" name="email" id="email" value={this.state.user.email} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="username">User name</Label>
                        <Input type="text" name="username" id="username" value={this.state.user.userName} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="firstname">First name</Label>
                        <Input type="text" name="firstname" id="firstname" value={this.state.user.firstName} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="lastname">Last name</Label>
                        <Input type="text" name="lastname" id="lastname" value={this.state.user.lastName} />
                    </FormGroup>
                    <Button onClick={this.update}>Update</Button>
                </Form>});
        else
            this.setState({details: ""});
    }


    /**
     * Updating data accross network...
     */
    update() {

        console.log("updating user details.");
    }


    render() {

        const capitalize = (s) => {
            if (typeof s !== 'string') return ''
            return s.charAt(0).toUpperCase() + s.slice(1)
        };

        const message =  this.state.isLoading ? "..." : this.state.user ?
            <h2>{capitalize(this.state.user.firstName)} {this.state.user.lastName} - Profile</h2> :
            <p>Error - Should be redirected...</p>;

        const button =  this.state.isLoading ? "..." : this.state.isAuthenticated ?
            <Jumbotron>
                <p>Click on the following button in case you need to update the fields</p>
                <Button color="primary" onClick={this.edit}>Edit</Button>
            </Jumbotron>
            :
            <strong>back off!</strong>;

        const loggedUser =  this.state.isLoading ? "..." : this.state.user ? this.state.user:undefined;
        const theDetails = this.state.isLoading ? "..." :
            <div>
                <Row>
                    <Col>Firstname :</Col>
                    <Col>{loggedUser.firstName}</Col>
                </Row>

                <Row>
                    <Col>Lastname :</Col>
                    <Col>{loggedUser.lastName}</Col>
                </Row>
                <Row>
                    <Col>User name :</Col>
                    <Col>{loggedUser.userName}</Col>
                </Row>
                <Row>
                    <Col>Email address :</Col>
                    <Col>{loggedUser.email}</Col>
                </Row>
            </div>
        ;

        return (
            <div>
                <AppNavBar user={loggedUser} />
                <Container fluid>
                    {message}
                    {button}
                    {theDetails}
                    {this.state.details}
                </Container>
            </div>
        );
    }
}

export default withCookies(User);