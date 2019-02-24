import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';
import logo from './rhm.png';
import PropTypes from 'prop-types'; // ES6

export default class AppNavBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
        };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {

        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
            <NavbarToggler onClick={this.toggle}/>
            <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="ml-auto" navbar>
                    <NavItem>
                        <NavLink
                            href="https://cloudcompta.net/">Cloud Compta</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="https://github.com/RHMAccounting/spring-allacc">GitHub</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="/user/profile">{this.props.user ? this.props.user.firstName:""} profile</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>;
    }
}