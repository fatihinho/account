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

export default class NewCustomerPage extends Component {
    constructor(props) {
        super(props);
        this.state = { name: '', surname: '' }

        this.updateNameValue = this.updateNameValue.bind(this);
        this.updateSurnameValue = this.updateSurnameValue.bind(this);
        this.onHandleSubmit = this.onHandleSubmit.bind(this); // this kullanmak için bind gerekli
    }

    updateNameValue(e) {
        this.setState({
            name: e.target.value
        });
    }

    updateSurnameValue(e) {
        this.setState({
            surname: e.target.value
        });
    }

    onHandleSubmit() {
        const name = this.state.name
        const surname = this.state.surname;
        if (name.length > 0 && surname.length > 0) {
            fetch('/v1/customer', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name: this.state.name, surname: this.state.surname })
            })
                .then(window.alert(`Kayıt Başarılı, Hoş Geldin ${name}!`))
                .then(this.props.history.push('/'))
        } else {
            window.alert("HATA: Eksik Alan Mevcut!");
        }
        this.setState({
            name: '',
            surname: ''
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
                                    <InputGroupText>Name</InputGroupText>
                                </InputGroupAddon>
                                <Input
                                    type="text"
                                    value={this.state.name} onChange={e => this.updateNameValue(e)} />
                            </InputGroup>
                        </FormGroup>
                        <br />
                        <FormGroup>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText>Surname</InputGroupText>
                                </InputGroupAddon>
                                <Input
                                    type="text"
                                    value={this.state.surname} onChange={e => this.updateSurnameValue(e)} />
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