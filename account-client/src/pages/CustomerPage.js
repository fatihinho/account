import { Component } from "react";

export default class CustomerPage extends Component {
    constructor(props) {
        super(props);
        this.state = { customer: Object() };
    }

    componentDidMount() {
        const id = this.props.location.state.id;
        fetch(`/v1/customer/${id}`)
            .then(response => response.json())
            .then(data => this.setState({ customer: data }));
    }

    render() {
        const { customer } = this.state;
        return (
            <div className="customer">
                <h3>{customer.name} {customer.surname}</h3>
            </div>
        );
    }
}