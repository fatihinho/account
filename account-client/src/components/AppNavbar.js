import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Navbar, NavbarBrand, Button } from 'reactstrap';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = { isOpen: false };
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <Navbar style={{ padding: "16px" }} color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">AccountApp</NavbarBrand>
            <Button className="float-right" color="success" tag={Link} to="/new-customer">New Customer</Button>
        </Navbar>;
    }
}