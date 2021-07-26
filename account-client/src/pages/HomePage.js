import { Component } from "react";
import { Link } from "react-router-dom";
import { Container, Button } from "reactstrap";


export default class HomePage extends Component {
    render() {
        return (
            <div>
                <Container className="customer-list" fluid>
                    <Link to="/customers"><Button type="button" style={{ borderColor: "transparent", backgroundColor: "darkorange" }}>Customers</Button></Link>
                </Container>
            </div>
        );
    }
}
