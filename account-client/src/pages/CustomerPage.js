import { Component } from "react";
import { Link } from "react-router-dom";
import { Button } from "reactstrap";

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
        let fetchedData = false;
        // veri'yi çektikten sonra
        if (Object.keys(customer).length > 0) {
            fetchedData = true;
        }
        return (
            <div className="customer">
                <h3>
                    {customer.name} {customer.surname}
                    <Link to={{
                        state: {
                            id: customer.id
                        },
                        pathname: '/add-account'
                    }}><Button style={{ marginLeft: '16px' }} color="primary">
                            Add Account
                        </Button></Link>
                </h3>
                <div className="accounts">
                    <h5>Accounts</h5>
                    {fetchedData ? customer.accounts.map(account =>
                        <div key={account.id}>
                            <Button style={{ margin: "8px" }} type="button">Balance: {account.balance}</Button>
                        </div>
                    ) : ""}
                </div>
            </div>
        );
    }
}