import { Component } from "react";

import {
    InputGroup,
    InputGroupAddon,
    InputGroupText,
    Input,
    Button,
    Form,
    FormGroup,
} from "reactstrap";

export default class AddAccountPage extends Component {
    constructor(props) {
        super(props);
        this.state = { initialCredit: Number() }

        this.updateInitialCreditValue = this.updateInitialCreditValue.bind(this);
        this.onHandleSubmit = this.onHandleSubmit.bind(this); // this kullanmak için bind gerekli
    }

    updateInitialCreditValue(e) {
        this.setState({
            initialCredit: e.target.value
        });
    }

    onHandleSubmit() {
        const id = this.props.location.state.id;
        const initialCredit = this.state.initialCredit;
        if (initialCredit > 0) {
            fetch('/v1/account', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ customerId: id, initialCredit: initialCredit })
            })
                .then(window.alert(`Hesap Başarıyla Eklendi`))
                .then(this.props.history.push('/'))
        } else {
            window.alert("HATA: Eksik Alan Mevcut!");
        }
        this.setState({
            initialCredit: '',
        });

    }

    render() {
        return (
            <div className="new-customer-form">
                <div style={{ margin: "64px", width: "25%" }}>
                    <Form role="form" onSubmit={this.onHandleSubmit}>
                        <FormGroup>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText>Initial Credit</InputGroupText>
                                </InputGroupAddon>
                                <Input
                                    type="number"
                                    value={this.state.initialCredit} onChange={e => this.updateInitialCreditValue(e)} />
                            </InputGroup>
                        </FormGroup>
                        <br />
                        <FormGroup>
                            <Button type="submit" style={{ float: "right" }} color="primary">Create</Button>{' '}
                        </FormGroup>
                    </Form>
                </div>
            </div >
        );
    }
}