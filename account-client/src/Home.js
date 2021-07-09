import { Component } from "react";
import { Link } from "react-router-dom";
import { Container, Button } from "reactstrap";


export default class Home extends Component {
    render() {
        return (
            <div>
                <Container fluid>
                    <Button color="link"><Link to="/customers">Customers</Link></Button>
                </Container>
            </div>
        );
    }
}
