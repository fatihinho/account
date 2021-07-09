import { Component } from "react";
import { Link } from "react-router-dom";


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
                {customers.map(customer =>
                    <div key={customer.id}>
                        <Link to={{
                            state: {
                                id: customer.id
                            },
                            pathname: '/customers/' + customer.id
                        }}>{customer.name} {customer.surname}</Link>
                    </div>
                )
                }
            </div>
        );
    }
}