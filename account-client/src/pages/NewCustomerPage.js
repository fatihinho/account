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
            window.alert(`İsim: ${name} Soyisim: ${surname}`);
        } else {
            window.alert("Eksik Alan Mevcut!");
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
                    <Form role="form">
                        <FormGroup>
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText>Name</InputGroupText>
                                </InputGroupAddon>
                                <Input
                                    type="text"
                                    value={this.state.name} onChange={e => this.updateNameValue(e)} />
                            </InputGroup>
                            <br />
                            <InputGroup>
                                <InputGroupAddon addonType="prepend">
                                    <InputGroupText>Surname</InputGroupText>
                                </InputGroupAddon>
                                <Input
                                    type="text"
                                    value={this.state.surname} onChange={e => this.updateSurnameValue(e)} />
                            </InputGroup>
                            <br />
                            <Button onClick={this.onHandleSubmit} style={{ float: "right" }} color="primary">Create</Button>{' '}
                        </FormGroup>
                    </Form>
                </div>
            </div >
        );
    }
}