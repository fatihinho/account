import { Component } from "react";


export default class CustomerList extends Component {
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
            <div className="App">
                <h2>Customers</h2>
                {customers.map(customer =>
                    <div key={customer.id}>
                        {customer.name} {customer.surname}
                    </div>
                )}
            </div>
        );
    }
}