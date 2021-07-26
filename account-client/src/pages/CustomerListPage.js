import { Component } from "react";
import { Link } from "react-router-dom";
import { Button } from "reactstrap";


export default class CustomerListPage extends Component {
    constructor(props) {
        super(props);
        this.state = { customers: [] };
    }

    componentDidMount() {
        fetch('/v1/customer/all')
            .then(response => response.json())
            .then(data => this.setState({ customers: data }));
    }

    render() {
        const { customers } = this.state;
        return (
            <div className="customer-list">
                <h2>Customers</h2>
                {customers.length > 0 ? customers.map(customer =>
                    <div key={customer.id}>
                        <Link to={{
                            state: {
                                id: customer.id
                            },
                            pathname: '/customers/' + customer.id
                        }}><Button style={{ margin: "8px" }} color="danger">{customer.name} {customer.surname}</Button></Link>
                    </div>
                )
                    : ""}
            </div>
        );
    }
}